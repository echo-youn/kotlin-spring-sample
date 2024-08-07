package com.example.webmvcsample

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/webmvc/sample")
class SampleController(
    private val myProperty: MyProperty,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping
    fun hello(): String = "Hello World"

    @PostMapping
    fun world(): String = "Hello World2"

    @PutMapping
    fun put(): String = "Hello World2"

    @DeleteMapping
    fun delete(): String = "Hello World2"

    @GetMapping("err")
    fun someThingWrong(): String {
        logger.info("property: $myProperty")
        throw RuntimeException("zzz")
    }
}
