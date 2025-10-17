package com.echoyoun.webfluxsecurity.service

import com.echoyoun.webfluxsecurity.repository.MyUserEntity
import com.echoyoun.webfluxsecurity.repository.MyUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service

@Service
class MyUserService(
    private val myUserRepository: MyUserRepository,
) {
    suspend fun findAll(): List<MyUserEntity> {
        return myUserRepository.findAll().toList()
    }
}