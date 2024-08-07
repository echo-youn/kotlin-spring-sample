package com.example.webmvcsecurity

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfiguration {
    // filter chain setting

    // security filter setting with httpSecurity(exposed by enabled security)
    @Order(1)
    @Bean
    fun securedFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity {
            csrf { disable() }

            // 분리하기
            securityMatcher("/secured/**")
            authorizeHttpRequests {
                authorize(anyRequest, authenticated)
//                authorize(anyRequest, hasRole("ADMIN"))
            }
        }
        return httpSecurity.build()
    }

    // Secondary filter
    @Bean
    fun h2FilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity {
            securityMatcher("/h2/**")
            authorizeHttpRequests {
                authorize(anyRequest, permitAll)
            }
        }
        return httpSecurity.build()
    }
}
