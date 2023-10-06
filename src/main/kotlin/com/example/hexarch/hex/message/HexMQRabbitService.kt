package com.example.hexarch.hex.message

import com.example.hexarch.hex.message.usecase.MessageUseCase
import org.springframework.stereotype.Service

private const val TEN = 10L
private const val HUNDRED = 100L

@Service
class HexMQRabbitService : MessageUseCase {
    override fun sendMessage() {
        mqRabbit()
        Thread.sleep(TEN)
    }

    fun mqRabbit() {
        Thread.sleep(HUNDRED)
    }
}
