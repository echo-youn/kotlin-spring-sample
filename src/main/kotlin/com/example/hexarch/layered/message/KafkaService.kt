package com.example.hexarch.layered.message

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class KafkaService {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Async
    fun sendMessage() {
        Thread.sleep(5000)
        logger.info("message Sent!")
    }
}
