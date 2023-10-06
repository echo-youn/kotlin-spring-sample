package com.example.hexarch.layered.user

import com.example.hexarch.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long>, UserRepositoryCustom {
    fun getUserById(id: Long): UserEntity?
}
