package com.example.hexarch.layered.config

import org.apache.el.util.ConcurrentCache
import org.springframework.cache.Cache
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableCaching
class CacheConfig {

    @Bean
    fun cacheManager(): CacheManager {
        val cacheManager = SimpleCacheManager()
        val cache: Cache = ConcurrentMapCache("simpleMyCache")
        cacheManager.setCaches(mutableListOf(cache))
        return cacheManager
    }
}
