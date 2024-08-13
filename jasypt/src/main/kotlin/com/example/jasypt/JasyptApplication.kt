package com.example.jasypt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class JasyptApplication

fun main(args: Array<String>) {
    runApplication<JasyptApplication>(*args)
}
