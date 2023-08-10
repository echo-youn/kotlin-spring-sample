package com.example.hexarch.hex.message

import com.example.hexarch.hex.message.usecase.MessageUseCase
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HexMessageController(
    @Qualifier("hexKafkaService")
    private val messageService: MessageUseCase
) {
    @PostMapping("/hex/message")
    fun receiveMessage() {
        messageService.sendMessage()
    }
}
