package com.example.websocket

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import java.util.concurrent.ConcurrentLinkedQueue

@Controller
class WebSocketController {
    private val chat = ConcurrentLinkedQueue<String>()

    @MessageMapping("/message1")
    @SendTo("/topic/message1")
    fun chat(message: String): String {
        return "response: $message"
    }
}
