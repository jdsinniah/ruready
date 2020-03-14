package com.ruready.app.service.`interface`

import com.ruready.app.hibernate.dao.User

interface UserService {
    fun saveUser(user: User): User
    fun getUserById(id: Long): User?
    fun getAllUsers(): List<User>
    fun getUserByEmail(email: String): User?
}