package com.example.webmvcsecurity

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JWTTokenFilter(
    private val userDetailsService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val token: String? = request.cookies.firstOrNull {
            it.name == "myCookie"
        }?.value

        if (token != null) {
            val u = userDetailsService.loadUserByUsername(token)
            SecurityContextHolder.getContext().authentication = AAA(
                u.username,
                u.password,
                u.authorities.toMutableList(),
                true
            )
        }
        filterChain.doFilter(request, response)
    }

    data class AAA(
        val username: String,
        val password: String,
        val auths: List<GrantedAuthority>,
        val isAuth: Boolean,
    ) : AbstractAuthenticationToken(auths) {
        override fun getCredentials(): String {
            return password
        }

        override fun getPrincipal(): AAA {
            return this
        }

        override fun isAuthenticated(): Boolean {
            return isAuth
        }
    }
}
