package com.example.webmvcsecurity

import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfiguration {
    // filter chain setting

    // security filter setting with httpSecurity(exposed by enabled security)
    @Order(1)
    @Bean
    fun securedFilterChain(
        httpSecurity: HttpSecurity,
        uds: UserDetailsService,
        jwtTokenFilter: JWTTokenFilter,
    ): SecurityFilterChain {
        httpSecurity {
            csrf { disable() }
            formLogin { disable() }
            httpBasic { disable() }
            logout { deleteCookies("myCookie") }
            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }
            addFilterBefore<UsernamePasswordAuthenticationFilter>(jwtTokenFilter)

            authorizeHttpRequests {
                authorize("/login", permitAll)
                authorize(anyRequest, authenticated)
            }
            exceptionHandling {
                authenticationEntryPoint = HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)
            }
        }
        return httpSecurity.build()
    }

    // Secondary filter 근데 작동 안함... 그래서 web security에서 아예 무시하게 함
    @Bean
    fun h2FilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity {
            securityMatcher(PathRequest.toH2Console())
            csrf { ignoringRequestMatchers(PathRequest.toH2Console()) }
            authorizeHttpRequests {
                authorize(anyRequest, permitAll)
            }
            headers { frameOptions { sameOrigin } }
        }
        return httpSecurity.build()
    }

    // 걍 무시해버림
    @Bean
    fun configureWebSecurity(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring().requestMatchers(PathRequest.toH2Console())
        }
    }

    // authentication manager로 authenticate 함
    // 실패하면 entrypoint로
    // 성공하면 고고
    @Bean
    fun authenticationManager(
        userDetailsService: UserDetailsService,
        passwordEncoder: PasswordEncoder,
    ): AuthenticationManager {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(userDetailsService)
        authenticationProvider.setPasswordEncoder(passwordEncoder)

        return ProviderManager(authenticationProvider)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8()
}
