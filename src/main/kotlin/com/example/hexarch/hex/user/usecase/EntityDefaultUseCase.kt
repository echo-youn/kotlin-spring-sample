package com.example.hexarch.hex.user.usecase

import org.springframework.transaction.annotation.Transactional

@Transactional
interface EntityDefaultUseCase<T, I> {
    fun createEntity(entity: T): T?

    @Transactional(readOnly = true)
    fun readEntityById(id: I): T?

    @Transactional(readOnly = true)
    fun readAll(): List<T>

    fun updateEntity(entity: T): T?

    fun deleteEntity(entity: T): T?
}
