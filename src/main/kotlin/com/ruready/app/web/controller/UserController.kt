package com.ruready.app.web.controller

import com.ruready.app.hibernate.dao.User
import com.ruready.app.service.`interface`.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
        @Autowired val userService: UserService
) {
    @PostMapping("/save")
    fun saveUser(@RequestBody user: User) = userService.saveUser(user)

    @GetMapping("/{id}")
    fun findUser(@PathVariable id: Long) = userService.getUserById(id)

    @GetMapping("/")
    fun findUserByEmail(@RequestParam email: String) = userService.getUserByEmail(email)

    @GetMapping("/all")
    fun getAllUsers() = userService.getAllUsers()
}