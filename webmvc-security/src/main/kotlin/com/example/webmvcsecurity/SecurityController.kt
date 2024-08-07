package com.example.webmvcsecurity

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class SecurityController(
    private val myUserRepository: MyUserRepository,
) {
    @GetMapping("/secured")
    fun secured(
        @AuthenticationPrincipal auth: Principal,
    ) {
        val u = myUserRepository.findAll()
        println(auth)
    }

    @GetMapping
    fun unsecured(
        @AuthenticationPrincipal auth: Principal,
    ) {

        val u = myUserRepository.findAll()
        println(auth)
    }
}
