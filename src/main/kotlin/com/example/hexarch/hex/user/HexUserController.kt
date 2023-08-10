package com.example.hexarch.hex.user

import com.example.hexarch.entity.UserEntity
import com.example.hexarch.hex.user.usecase.EntityDefaultUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HexUserController(
    private val userService: EntityDefaultUseCase<UserEntity, Long>
) {
    @GetMapping("/hex/user")
    fun getUser(id: Long): ResponseEntity<UserEntity> = userService.readEntityById(id)?.let {
        ResponseEntity.ok(it)
    } ?: ResponseEntity.noContent().build()

    @GetMapping("/hex/users")
    fun getAllUsers(): List<UserEntity> = userService.readAll()
}
