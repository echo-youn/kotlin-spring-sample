package com.example.webmvcsecurity

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SecurityController {
    @GetMapping("/secured")
    fun secured() {

    }

    @GetMapping
    fun unsecured() {

    }
}
