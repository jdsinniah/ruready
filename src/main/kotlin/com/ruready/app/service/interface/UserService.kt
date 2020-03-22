package com.ruready.app.service.`interface`

import com.ruready.app.hibernate.dao.User
import org.springframework.security.core.userdetails.UserDetailsService

interface UserService: UserDetailsService {
    fun saveUser(user: User): User
    fun getUserById(id: Long): User?
    fun getAllUsers(): List<User>
    fun getUserByEmail(email: String): User?
}