package com.example.hexarch.layered.user

import com.example.hexarch.entity.UserEntity

interface UserRepositoryCustom {
    fun somethingSpecial(id: List<Long>): List<UserEntity>

    fun userWithGames(id: List<Long>): List<UserEntity>
}
