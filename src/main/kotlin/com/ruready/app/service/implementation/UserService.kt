package com.ruready.app.service.implementation

import com.ruready.app.hibernate.dao.User
import com.ruready.app.hibernate.dao.UserRepository
import com.ruready.app.service.`interface`.UserService
import org.apache.commons.codec.digest.DigestUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class UserService(
        @Autowired val userRepository: UserRepository<Long>
): UserService {
    override fun saveUser(user: User): User {
        user.password = DigestUtils.sha256Hex(user.password)
        return userRepository.save(user)
    }

    override fun getUserById(id: Long): User? = userRepository.findUserById(id)

    override fun getAllUsers() = userRepository.findAll().toList()

    override fun getUserByEmail(email: String): User? = userRepository.findUserByEmail(email)

    override fun loadUserByUsername(username: String?): UserDetails? {
        if(username != null) {
            return getUserByEmail(username)
        }
        return null
    }
}