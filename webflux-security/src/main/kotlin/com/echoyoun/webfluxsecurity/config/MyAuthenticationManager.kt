package com.echoyoun.webfluxsecurity.config

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationDeniedException
import org.springframework.security.authorization.AuthorizationResult
import org.springframework.security.authorization.ReactiveAuthorizationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authorization.AuthorizationContext
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class MyAuthenticationManager(
    private val authProvider: AuthenticationProvider,
) : ReactiveAuthorizationManager<AuthorizationContext>, ReactiveAuthenticationManager {
    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        val a = authProvider.authenticate(authentication) ?: throw AuthorizationDeniedException("")
        return Mono.just(a)
    }

    override fun authorize(
        authentication: Mono<Authentication>,
        `object`: AuthorizationContext,
    ): Mono<AuthorizationResult> {
        val a = AuthorizationDecision(true)
        return Mono.just(a)
    }
}
