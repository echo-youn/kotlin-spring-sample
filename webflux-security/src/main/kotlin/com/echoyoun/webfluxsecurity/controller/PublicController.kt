package com.echoyoun.webfluxsecurity.controller

import com.echoyoun.webfluxsecurity.service.MyUserService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import reactor.netty.http.server.HttpServerRequest

@RestController
@RequestMapping("/public")
class PublicController(
    private val myUserService: MyUserService,
) {
    private val logger = org.slf4j.LoggerFactory.getLogger(this::class.java)

    @RequestMapping(method = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE])
    suspend fun public(req: HttpServerRequest): String {
        val users = myUserService.findAll()
        logger.info("users: $users")

        return req.method().name() + " public"
    }
}
