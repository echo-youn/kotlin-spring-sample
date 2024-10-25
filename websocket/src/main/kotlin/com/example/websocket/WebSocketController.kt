package com.example.websocket

import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.stereotype.Controller
import org.springframework.web.socket.messaging.SessionConnectEvent
import org.springframework.web.socket.messaging.SessionDisconnectEvent
import java.time.LocalDateTime
import java.util.concurrent.ConcurrentLinkedQueue


@Controller
class WebSocketController(
    private val simpleMessageSendingOperations: SimpMessageSendingOperations,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)
    private val chat = ConcurrentLinkedQueue<String>()

    data class Simpletest(
        val message: String,
        val receivedAt: LocalDateTime = LocalDateTime.now(),
    )

    // 새로운 사용자가 웹 소켓을 연결할 때 실행됨
    // @EventListener은 한개의 매개변수만 가질 수 있다.
    @EventListener
    fun handleWebSocketConnectListener(event: SessionConnectEvent?) {
        logger.info("Received a new web socket connection")
    }

    // 사용자가 웹 소켓 연결을 끊으면 실행됨
    @EventListener
    fun handleWebSocketDisconnectListener(event: SessionDisconnectEvent) {
        val headerAccesor = StompHeaderAccessor.wrap(event.message)
        val sessionId = headerAccesor.sessionId

        logger.info("sessionId Disconnected : $sessionId")
    }

    @MessageMapping("/message1")
    @SendTo("/topic/message1")
    fun chat(
        message: Message<Simpletest>,
        @Payload payload: Simpletest,
    ): String {
//        val a = StompHeaderAccessor.wrap(message)
//        val s = a.sessionId
        simpleMessageSendingOperations.send(message)
        return "response: $message, now: ${LocalDateTime.now()}"
    }
}
