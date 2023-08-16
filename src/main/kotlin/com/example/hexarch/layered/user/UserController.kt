package com.example.hexarch.layered.user

import com.example.hexarch.entity.UserEntity
import com.example.hexarch.service.User2Service
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
    private val user2Service: User2Service
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/java/user")
    fun test(): UserEntity {
        return user2Service.a()
    }

    @GetMapping("/user")
    fun getUser(
        @RequestParam
        id: Long
    ): ResponseEntity<UserEntity> = userService.readUser(id)?.let {
        ResponseEntity.ok(it)
    } ?: ResponseEntity.noContent().build()

    @GetMapping("/users")
    fun getAllUsers(
        @RequestParam(required = false) // 추가 tip.
        id: List<Long>?
    ): List<UserEntity> = if (id.isNullOrEmpty()) {
        userService.getAllUsers()
    } else {
        userService.special(id)
    }

    @PostMapping("/user")
    fun createUser(
        @RequestBody
        user: UserEntity
    ): UserEntity = userService.createUser(user)

    @PutMapping("/user/{userId}")
    fun updateUser(
        @PathVariable
        userId: Long,
        @RequestBody
        user: UserEntity
    ): ResponseEntity<UserEntity> {
        if (userId != user.id) {
            return ResponseEntity.badRequest().build()
        }

        return userService.updateUser(user)
            ?.let {
                ResponseEntity.ok(it)
            } ?: ResponseEntity.internalServerError().build()
    }
}
