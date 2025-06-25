package com.example.webmvcjpa

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MyUserService(
    private val myUserRepository: MyUserRepository,
) {
    @Transactional(readOnly = true)
    suspend fun findAll(): List<MyUserDto> {
        val users = myUserRepository.findAll()
        return users.map { it.toDto() }
    }

    fun save(
        username: String,
    ): MyUserDto {
        val u = MyUserEntity(
            id = 0,
            username = username,
            password = "password",
            mobile = "",
            roles = "",
        )
        val r = myUserRepository.save(u)
        return r.toDto()
    }
}
