package com.example.sample

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController {
    @GetMapping("/")
    fun index(): ResponseEntity<String> {
        return ResponseEntity.ok("hello World")
    }
}
