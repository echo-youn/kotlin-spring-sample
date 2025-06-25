package com.example.webmvcjpa

import org.springframework.data.jpa.repository.JpaRepository

interface MyUserRepository : JpaRepository<MyUserEntity, Long> {
}
