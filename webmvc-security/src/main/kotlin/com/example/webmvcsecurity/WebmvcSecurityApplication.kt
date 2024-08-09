package com.example.webmvcsecurity

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@SpringBootApplication
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
class WebmvcSecurityApplication

fun main(args: Array<String>) {
    runApplication<WebmvcSecurityApplication>(*args)
}
