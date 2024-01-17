package com.example.hexarch.layered.message

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class MessageCron(
    private val messageService: MQRabbitService
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Scheduled(cron = "\${cron.message}")
    fun generateMessage() {
        log.info("test")
        messageService.sendMessage()
    }
}
