package com.example.jasypt

import org.jasypt.encryption.StringEncryptor
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class SampleService(
    @Value("\${app.message}")
    private val message: String,
    private val jasyptStringEncryptor: StringEncryptor,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Scheduled(initialDelay = 1_000, fixedDelay = 3_000)
    fun test() {
        logger.info(message)
        val a = jasyptStringEncryptor.encrypt(message)
        val b = jasyptStringEncryptor.decrypt(a)
        println(b)
    }
}
