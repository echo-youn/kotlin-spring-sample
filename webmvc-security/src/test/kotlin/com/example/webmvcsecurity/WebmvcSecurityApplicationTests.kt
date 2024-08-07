package com.example.webmvcsecurity

import org.h2.server.web.WebServer
import org.junit.jupiter.api.Test

class WebmvcSecurityApplicationTests {

    @Test
    fun contextLoads() {
        println(WebServer.encodeAdminPassword("password123123"))
    }

}
