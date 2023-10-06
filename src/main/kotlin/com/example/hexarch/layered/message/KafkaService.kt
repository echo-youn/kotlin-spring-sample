package com.example.hexarch.layered.message

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

private const val FIVE_THOUSANDS = 5000L

@Service
class KafkaService {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Async
    fun sendMessage() {
        Thread.sleep(FIVE_THOUSANDS)
        logger.info("message Sent!")
    }
}
