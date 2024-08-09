package com.example.webmvcsecurity

import com.example.webmvcsecurity.JWTTokenFilter.MyAuthenticationPrincipal
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize
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
        @AuthenticationPrincipal authentication: MyAuthenticationPrincipal,
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

    /**
     * @see com.example.webmvcsecurity.SecurityConfiguration:47
     */
    @GetMapping("/secured/admin")
    fun pathMatcherAuthorizingOnlyAdmin(
        @AuthenticationPrincipal authentication: MyAuthenticationPrincipal,
    ) {
        val u = myUserRepository.findAll()
        println(authentication)
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/secured/admin2")
    fun methodSecuredAuthorizingOnlyAdmin(
        @AuthenticationPrincipal authentication: MyAuthenticationPrincipal,
    ) {
        val u = myUserRepository.findAll()
        println(authentication)
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/secured/admin3")
    fun methodPreAuthAuthorizingOnlyAdmin(
        @AuthenticationPrincipal authentication: MyAuthenticationPrincipal,
    ) {
        val u = myUserRepository.findAll()
        println(authentication)
    }

    @Secured("ROLE_USER")
    @GetMapping("/secured/user")
    fun userOnlyButHierarchy(
        @AuthenticationPrincipal authentication: MyAuthenticationPrincipal,
    ) {
        val u = myUserRepository.findAll()
        println(authentication)
    }

    @Secured("ROLE_WIRED")
    @GetMapping("/secured/wired")
    fun wiredOnlyButHierarchy(
        @AuthenticationPrincipal authentication: MyAuthenticationPrincipal,
    ) {
        val u = myUserRepository.findAll()
        println(authentication)
    }
}
