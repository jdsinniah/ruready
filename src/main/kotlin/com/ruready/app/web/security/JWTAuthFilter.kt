package com.ruready.app.web.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm.HMAC512
import com.fasterxml.jackson.databind.ObjectMapper
import com.ruready.app.service.`interface`.UserService
import com.ruready.app.web.dto.UserLoginDTO
import com.ruready.app.web.security.SecurityConstants.EXPIRATION_TIME
import com.ruready.app.web.security.SecurityConstants.HEADER_STRING
import com.ruready.app.web.security.SecurityConstants.SECRET
import com.ruready.app.web.security.SecurityConstants.TOKEN_PREFIX
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

object SecurityConstants {
    const val SECRET = "OnlyTheDeservingOnesAreMeantToKnow"
    const val EXPIRATION_TIME: Long = 864000000 // 10 days
    const val TOKEN_PREFIX = "Bearer "
    const val HEADER_STRING = "Authorization"
    const val SIGN_UP_URL = "/user/save"
}

class JWTAuthenticationFilter(
        private val authenticationManager: AuthenticationManager,
        @Autowired
        val userService: UserService? = null
): UsernamePasswordAuthenticationFilter() {

    override fun getAuthenticationManager(): AuthenticationManager {
        return authenticationManager
    }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication? {
        val userLoginDTO = ObjectMapper().readValue(request?.inputStream, UserLoginDTO::class.java)

        val user = userService?.getUserByEmail(userLoginDTO.email)
        return if(user != null && userLoginDTO.password == user.password)
            this.authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(
                            user.email,
                            user.password,
                            user.roles
                    )
            )
        else
            throw AuthenticationServiceException("Invalid email or password!")
    }

    override fun successfulAuthentication(req: HttpServletRequest?,
                                          res: HttpServletResponse,
                                          chain: FilterChain?,
                                          auth: Authentication) {
        val token = JWT.create()
                .withSubject((auth.principal as User).username)
                .withExpiresAt(Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.toByteArray()))
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token)
    }

}


class JWTAuthorizationFilter(authManager: AuthenticationManager?) : BasicAuthenticationFilter(authManager) {

    override fun doFilterInternal(req: HttpServletRequest,
                                  res: HttpServletResponse,
                                  chain: FilterChain) {
        val header = req.getHeader(HEADER_STRING)
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res)
            return
        }
        val authentication = getAuthentication(req)
        SecurityContextHolder.getContext().authentication = authentication
        chain.doFilter(req, res)
    }

    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val token = request.getHeader(HEADER_STRING)
        if (token != null) {
            // parse the token.
            val user = JWT.require(HMAC512(SECRET.toByteArray()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .subject
            return if (user != null) {
                UsernamePasswordAuthenticationToken(user, null, ArrayList())
            } else null
        }
        return null
    }
}
