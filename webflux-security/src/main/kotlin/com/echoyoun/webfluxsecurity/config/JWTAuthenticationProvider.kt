package com.echoyoun.webfluxsecurity.config

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class JWTAuthenticationProvider: AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        return UsernamePasswordAuthenticationToken(authentication.name, authentication.credentials)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return true
    }
}
