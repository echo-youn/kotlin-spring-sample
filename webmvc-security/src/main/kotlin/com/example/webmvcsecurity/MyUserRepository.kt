package com.example.webmvcsecurity

import org.springframework.data.jpa.repository.JpaRepository

interface MyUserRepository : JpaRepository<MyUser, Long> {
    fun findByUsername(username: String): MyUser?
}
