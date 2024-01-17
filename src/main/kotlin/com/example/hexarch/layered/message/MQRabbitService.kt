package com.example.hexarch.layered.message

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

private const val TEN = 1_000L

@Service
class MQRabbitService {
    private val logger = LoggerFactory.getLogger(this::class.java)
    
    fun sendMessage() {
        Thread.sleep(TEN)
        logger.info("message Sent!")
    }
}
