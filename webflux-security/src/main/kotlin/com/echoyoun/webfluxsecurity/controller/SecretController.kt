package com.echoyoun.webfluxsecurity.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import reactor.netty.http.server.HttpServerRequest

@RestController
@RequestMapping("/secret")
class SecretController {
    @RequestMapping(method = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE])
    suspend fun secret(req: HttpServerRequest) = req.method().name() + " secret"
}
