package com.echoyoun.webfluxsecurity.repository

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class MyUserEntity(
    @Id
    val id: Long,
    val username: String,
    val password: String,
    val email: String,
    val enabled: Boolean,
)