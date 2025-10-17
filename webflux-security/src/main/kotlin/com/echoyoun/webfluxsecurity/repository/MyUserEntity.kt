package com.echoyoun.webfluxsecurity.repository

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class MyUserEntity(
    @Id
    val userame: String,
    val password: String,
    val email: String,
    val enabled: Boolean,
)