package com.echoyoun.webfluxsecurity.controller

import com.echoyoun.webfluxsecurity.service.MyUserService
import org.slf4j.LoggerFactory
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/secret")
class SecretController(
    private val myUserService: MyUserService,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @RequestMapping(method = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE])
    suspend fun secret(req: ServerHttpRequest): String {
        val users = myUserService.findAll()
        logger.info("users: $users")
        return req.method.name() + " secret"
    }
}
