package com.example.webmvcstream

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class WebmvcStreamApplication

fun main(args: Array<String>) {
    runApplication<WebmvcStreamApplication>(*args)
}
