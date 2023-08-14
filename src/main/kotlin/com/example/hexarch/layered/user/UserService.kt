package com.example.hexarch.layered.user

import com.example.hexarch.entity.UserEntity
import com.example.hexarch.layered.message.KafkaService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserService(
    private val userRepository: UserRepository,
    private val kafkaService: KafkaService
) {
    fun createUser(user: UserEntity): UserEntity = userRepository.save(user)

    @Transactional(readOnly = true)
    fun readUser(id: Long): UserEntity? = userRepository.getUserById(id)

    fun updateUser(user: UserEntity): UserEntity? = userRepository.getUserById(user.id)
        ?.also {
            it.id = user.id
            it.username = user.username
        }?.also {
            kafkaService.sendMessage()
        }

    fun deleteUser(user: UserEntity): UserEntity? = userRepository.getUserById(user.id)
        ?.also { userRepository.delete(it) }

    fun special(id: List<Long>) = userRepository.somethingSpecial(id)

    @Transactional(readOnly = true)
    fun getAllUsers(): List<UserEntity> = userRepository.findAll()
}
