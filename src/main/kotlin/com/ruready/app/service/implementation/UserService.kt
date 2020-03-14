package com.ruready.app.service.implementation

import com.ruready.app.exception.UserNotFoundException
import com.ruready.app.hibernate.dao.User
import com.ruready.app.hibernate.dao.UserRepository
import com.ruready.app.service.`interface`.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(
        @Autowired val userRepository: UserRepository
): UserService {
    override fun saveUser(user: User) = userRepository.save(user)

    override fun getUserById(id: Long): User? = userRepository
            .findById(id)
            .orElseThrow {UserNotFoundException("User with $id")}

    override fun getAllUsers() = userRepository.findAll().toList()

    override fun getUserByEmail(email: String): User? = userRepository
            .findUserByEmail(email)
            .orElseThrow {UserNotFoundException("User with $email")}
}