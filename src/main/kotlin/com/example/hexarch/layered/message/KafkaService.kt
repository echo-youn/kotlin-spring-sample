package com.example.hexarch.layered.message

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class KafkaService {
    @Async
    fun sendMessage() {
        Thread.sleep(10)
    }
}
