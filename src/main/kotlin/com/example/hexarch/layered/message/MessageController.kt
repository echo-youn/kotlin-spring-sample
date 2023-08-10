package com.example.hexarch.layered.message

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(
    private val messageService: KafkaService
) {
    @PostMapping("message")
    fun messageReceive() {
        messageService.sendMessage()
    }
}
