package com.echoyoun.webfluxsecurity.repository

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface MyUserRepository : CoroutineCrudRepository<MyUserEntity, String> {
}
