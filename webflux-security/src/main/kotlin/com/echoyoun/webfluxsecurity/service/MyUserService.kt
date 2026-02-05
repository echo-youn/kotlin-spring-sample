package com.echoyoun.webfluxsecurity.service

import com.echoyoun.webfluxsecurity.repository.MyUserEntity
import com.echoyoun.webfluxsecurity.repository.MyUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

object ENUMS {
    const val USER = "USER"
    const val READ = "READ"
}

@Service
class MyUserService(
    private val myUserRepository: MyUserRepository,
) {
    @PreAuthorize("hasPermission('abc', 'abc')")
    suspend fun findAll(): List<MyUserEntity> {
        return myUserRepository.findAll().toList()
    }

    @PreAuthorize("hasPermission(#userId['userId'], '${ENUMS.USER}', '${ENUMS.READ}')")
    suspend fun findAllOnlyUserA(
        a: Long,
        userId: Map<String, String>,
    ): List<MyUserEntity> {
        return myUserRepository.findAll().toList()
    }
}