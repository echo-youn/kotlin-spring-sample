package com.echoyoun.webfluxsecurity.config

import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

class MySecurityFilter(
    private val authenticationManager: ReactiveAuthenticationManager,
) : WebFilter {
    override fun filter(
        exchange: ServerWebExchange,
        chain: WebFilterChain
    ): Mono<Void> {
        val path = exchange.request.uri
        val a = UsernamePasswordAuthenticationToken("user", "password", emptyList())
        authenticationManager.authenticate(a).block()
        return chain.filter(exchange)
//        return chain.filter(exchange).contextWrite {
//            ReactiveSecurityContextHolder.withAuthentication(
//                UsernamePasswordAuthenticationToken("user", "password", emptyList())
//            )
//        }
    }
}