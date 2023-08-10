package com.example.hexarch.hex.user

import com.example.hexarch.entity.UserEntity
import com.example.hexarch.hex.user.usecase.EntityDefaultUseCase
import com.example.hexarch.layered.user.UserRepository
import org.springframework.stereotype.Service

@Service
class HexUserService(
    private val userRepository: UserRepository
): EntityDefaultUseCase<UserEntity, Long> {
    override fun createEntity(entity: UserEntity): UserEntity? = userRepository.save(entity)

    override fun readEntityById(id: Long): UserEntity? {
        return userRepository.getUserById(id)
    }

    override fun readAll(): List<UserEntity> {
        return userRepository.findAll()
    }

    override fun updateEntity(entity: UserEntity): UserEntity? {
        return userRepository.getUserById(entity.id)
            ?.also {
                it.id = entity.id
                it.username = entity.username
            }
    }

    override fun deleteEntity(entity: UserEntity): UserEntity? {
        return userRepository.getUserById(entity.id)
            ?.also { userRepository.delete(it) }
    }
}
