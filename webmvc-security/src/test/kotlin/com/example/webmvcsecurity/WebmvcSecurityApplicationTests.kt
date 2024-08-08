package com.example.webmvcsecurity

import org.h2.server.web.WebServer
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder

class WebmvcSecurityApplicationTests {

    @Test
    fun contextLoads() {
        println(WebServer.encodeAdminPassword("password123123"))
    }

    @Test
    fun test() {
        val encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8()
        val r = encoder.encode("password1")
        println(r)
    }
}
