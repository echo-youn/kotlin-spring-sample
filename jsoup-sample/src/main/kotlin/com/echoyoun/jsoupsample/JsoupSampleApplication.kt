package com.echoyoun.jsoupsample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class JsoupSampleApplication

fun main(args: Array<String>) {
    runApplication<JsoupSampleApplication>(*args)
}
