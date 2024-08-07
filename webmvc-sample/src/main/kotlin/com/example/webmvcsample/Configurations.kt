package com.example.webmvcsample

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableConfigurationProperties
@ConfigurationPropertiesScan
class Configurations {
    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                // or use `@CrossOrigin`
                registry.addMapping("/api/**")
                    .allowedOrigins("https://localhost:8080")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("")
                    .allowCredentials(true)
                    .maxAge(3600)
            }

            override fun addInterceptors(registry: InterceptorRegistry) {
                super.addInterceptors(registry)
            }

            override fun configurePathMatch(configurer: PathMatchConfigurer) {
                super.configurePathMatch(configurer)
            }
        }
    }
}
