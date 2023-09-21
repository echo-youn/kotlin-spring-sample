package com.example.hexarch.layered.user

import com.example.hexarch.layered.game.Game

data class UserDto(
    val id: Long,
    val username: String,
    val games: List<Game>
)
