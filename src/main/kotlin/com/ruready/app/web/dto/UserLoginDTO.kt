package com.ruready.app.web.dto

import com.ruready.app.enumeration.Role

data class UserLoginDTO(
        val email: String,
        val password: String,
        val roles: Set<Role>,
        val token: String? = null)