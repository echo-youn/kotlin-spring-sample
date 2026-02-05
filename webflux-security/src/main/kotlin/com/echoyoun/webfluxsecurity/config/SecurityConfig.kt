package com.echoyoun.webfluxsecurity.config

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.access.PermissionEvaluator
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.invoke
import org.springframework.security.core.Authentication
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.NoOpAuthenticationEntryPoint
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.authentication.HttpBasicServerAuthenticationEntryPoint
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationEntryPoint
import org.springframework.security.web.server.authorization.HttpStatusServerAccessDeniedHandler
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository
import org.springframework.security.web.server.context.ReactorContextWebFilter
import org.springframework.security.web.server.util.matcher.AndServerWebExchangeMatcher
import org.springframework.security.web.server.util.matcher.NegatedServerWebExchangeMatcher
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers.pathMatchers
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
//@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig {
    val logger = LoggerFactory.getLogger(this::class.java)

    @Bean
    fun securityFilterChain(
        http: ServerHttpSecurity,
        myAuthenticationManager: ReactiveAuthenticationManager,
    ): SecurityWebFilterChain {
        return http {
            // securityFilterChain을 아래 경로 설정 및 제외
            securityMatcher(
                AndServerWebExchangeMatcher(
                    pathMatchers("/**"),
                    NegatedServerWebExchangeMatcher(
                        pathMatchers("/public/**", "/.well-known/**", "/favicon.ico", "/actuator/**")
                    ),
                )
            )

            // CSRF 설정
            csrf {
                disable()
            }

            // cors 설정
            cors {
                configurationSource = UrlBasedCorsConfigurationSource().apply {
                    registerCorsConfiguration(
                        "/**",
                        CorsConfiguration().apply {
                            allowedOriginPatterns = listOf(
                                "http://localhost:8080",
                                "https://stackoverflow.com"
                            )
                        }
                    )
                }
            }

            // Form Login 비활성화 (로그인 화면 미사용 및 기본 기능 미사용)
            formLogin { disable() }

            // HTTP Basic 비활성화
            httpBasic { disable() }

            // Authorization Manager 경로에 설정
            // permit을 해도 Filter는 탐
            authorizeExchange {
                authorize(pathMatchers("/public/**"), permitAll)
                // AuthorizationManager.authorize를 실행하여 권한이 있는지 확인
                authorize(pathMatchers("/**"), MyAuthenticationManager(JWTAuthenticationProvider()))
            }

            // 에러 헨들링
            exceptionHandling {
                // 로그인 실패시 띄우는 기능
                accessDeniedHandler = HttpStatusServerAccessDeniedHandler(HttpStatus.UNAUTHORIZED)

                // 로그인 하는 곳으로 이동
                authenticationEntryPoint = HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED)
            }

            // authentication Manager를 등록함 (authentication Manager)로 filter들에서 의존해 authorize 함수 실행해서 로그인 처리함
            authenticationManager = myAuthenticationManager

            // securityContext를 저장하는 레파지토리 (Web Session, NoOps)
            securityContextRepository = NoOpServerSecurityContextRepository.getInstance()

            // 필터를 등록한다.
            // 필터를 Component로 만들어서 주입받아서 쓰는경우 Bean으로 등록되어 2번 발생할 수 도있다.
            // 그리고 Security Context filter 외에 다른 필터체인에서 실행될 수 도 있음
            // Filter에서 Authentication Manager를 주입받아서 authenticate 해주어야 로그인 상태로 만듦
//            addFilterAt(MySecurityFilter(), SecurityWebFiltersOrder.AUTHORIZATION)
            addFilterAt(MySecurityFilter(myAuthenticationManager), SecurityWebFiltersOrder.AUTHENTICATION)
        }
    }

    @Bean
    fun methodSecurityExpressionHandler2(
        p: PermissionEvaluator,
    ): MethodSecurityExpressionHandler {
        val d = DefaultMethodSecurityExpressionHandler()
        d.setPermissionEvaluator(p)
        return d
    }
}
