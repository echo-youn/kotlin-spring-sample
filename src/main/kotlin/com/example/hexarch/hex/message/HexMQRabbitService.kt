package com.example.hexarch.hex.message

import com.example.hexarch.hex.message.usecase.MessageUseCase
import org.springframework.stereotype.Service

@Service
class HexMQRabbitService: MessageUseCase{
    override fun sendMessage() {
        mqRabbit()
        Thread.sleep(10)
    }

    fun mqRabbit() {
        Thread.sleep(100)
    }
}
