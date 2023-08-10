package com.example.hexarch.layered.message

import org.springframework.stereotype.Service

@Service
class MQRabbitService {
    fun sendMessage() {
        Thread.sleep(10)
    }
}
