package com.example.hexarch.layered.message

import org.springframework.stereotype.Service

private const val TEN = 10L

@Service
class MQRabbitService {
    fun sendMessage() {
        Thread.sleep(TEN)
    }
}
