package com.example.webmvcsecurity

import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
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
}
