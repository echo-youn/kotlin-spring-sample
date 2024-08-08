package com.example.webmvcsecurity

import com.example.webmvcsecurity.JWTTokenFilter.AAA
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SecurityController(
    private val myUserRepository: MyUserRepository,
    private val authenticationManager: AuthenticationManager,
) {
    @GetMapping("/secured")
    fun secured(
        @AuthenticationPrincipal authentication: AAA,
    ) {
        val u = myUserRepository.findAll()
        println(authentication)
    }

    @PostMapping("/login")
    fun login(
        @RequestParam username: String,
        @RequestParam password: String,
        request: HttpServletRequest,
        response: HttpServletResponse,
    ) {
        val t = UsernamePasswordAuthenticationToken.unauthenticated(
            username,
            password,
        )
        kotlin.runCatching {
            authenticationManager.authenticate(t)
        }.onSuccess { t ->
            val a = t.principal as MyUser
            response.addCookie(
                Cookie("myCookie", a.username).apply {
                    path = "/"
                    secure = true
                    isHttpOnly = true
                }
            )
        }.onFailure {
            val a = "failed"
        }
    }
}
