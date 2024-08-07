package com.example.webmvcsample

import org.springframework.boot.context.properties.ConfigurationProperties
import java.time.Duration

@ConfigurationProperties(prefix = "my-property")
data class MyProperty(
    val a: String,
    val b: Duration,
)
