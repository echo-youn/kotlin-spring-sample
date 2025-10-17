package com.echoyoun.webfluxsecurity.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import reactor.netty.http.server.HttpServerRequest

@RestController
@RequestMapping("/public")
class PublicController {
    @RequestMapping(method = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE])
    suspend fun public(req: HttpServerRequest) = req.method().name() + " public"
}
