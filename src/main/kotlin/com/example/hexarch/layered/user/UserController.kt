package com.example.hexarch.layered.user

import com.example.hexarch.entity.UserEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {
    @GetMapping("/user")
    fun getUser(id: Long): ResponseEntity<UserEntity> = userService.readUser(id)?.let {
        ResponseEntity.ok(it)
    } ?: ResponseEntity.noContent().build()

    @GetMapping("/users")
    fun getAllUsers(): List<UserEntity> = userService.getAllUsers()
}
