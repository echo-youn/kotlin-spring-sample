package com.example.hexarch.layered.user

import com.example.hexarch.entity.UserEntity
import org.springframework.data.repository.Repository

@org.springframework.stereotype.Repository
class UserMemoryRepository: Repository<UserEntity, Long> {
    // Not Thread-safe
    private val store: MutableMap<Long, UserEntity> = mutableMapOf()

    // Not Thread-safe
    private var seq: Long = 0L
    fun read(id: Long): UserEntity? = store[id]

    fun readAll(): List<UserEntity> = store.values.toList()

    fun write(user: UserEntity): UserEntity {
        val s = seq + 1
        seq = s
        val new = UserEntity(
            s,
            user.username
        )
        store[s] = new // null 이면 신규, Notnull이면 기존 업데이트
        return new
    }
}
