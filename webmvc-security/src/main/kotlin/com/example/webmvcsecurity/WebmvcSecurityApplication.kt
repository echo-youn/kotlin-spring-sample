package com.example.webmvcsecurity

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@SpringBootApplication
@EnableWebSecurity
class WebmvcSecurityApplication

fun main(args: Array<String>) {
    runApplication<WebmvcSecurityApplication>(*args)
}
