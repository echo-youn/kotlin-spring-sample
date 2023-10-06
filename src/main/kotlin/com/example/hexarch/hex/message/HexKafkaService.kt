package com.example.hexarch.hex.message

import com.example.hexarch.hex.message.usecase.MessageUseCase
import org.springframework.stereotype.Service

private const val TEN = 10L

@Service
class HexKafkaService : MessageUseCase {
    override fun sendMessage() {
        doSomethingInKafka()
        Thread.sleep(TEN)
    }

    fun doSomethingInKafka() {
        Thread.sleep(1)
    }
}
