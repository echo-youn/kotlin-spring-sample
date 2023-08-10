package com.example.hexarch.hex.message

import com.example.hexarch.hex.message.usecase.MessageUseCase
import org.springframework.stereotype.Service

@Service
class HexKafkaService: MessageUseCase {
    override fun sendMessage() {
        doSomethingInKafka()
        Thread.sleep(10)
    }

    fun doSomethingInKafka() {
        Thread.sleep(1)
    }
}
