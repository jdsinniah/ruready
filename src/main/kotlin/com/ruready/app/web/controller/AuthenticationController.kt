package com.ruready.app.web.controller

import com.ruready.app.web.dto.UserLoginDTO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/authenticate")
class AuthenticationController {

    @PostMapping("/login")
    fun authenticate(@RequestBody userLoginDTO: UserLoginDTO) {

    }

}