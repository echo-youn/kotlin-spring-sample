package com.example.webmvcjpa

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/suspend")
class SuspendController(
    private val myUserService: MyUserService,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/a")
    suspend fun a(): List<MyUserDto> {
        logger.info("a")
        delay(10_000)
        val r = myUserService.findAll()
        logger.info("a")
        return r
    }

    @GetMapping("/b")
    suspend fun b(
        @RequestParam a: String,
    ): List<MyUserDto> {
        logger.info("b: $a")
        delay(10)
        val r = myUserService.findAll()
        logger.info("b: $a")
        return r
    }

    @PostMapping("/c")
    suspend fun c(
        @RequestParam username: String,
    ): MyUserDto {
        logger.info("c: $username")
        delay(10)
        val r = withContext(Dispatchers.IO) {
            myUserService.save(username)
        }
        logger.info("c: $username")
        return r
    }
}
