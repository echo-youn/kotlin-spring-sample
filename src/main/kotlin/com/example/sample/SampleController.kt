package com.example.sample

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController {
    @GetMapping("/")
    fun index(): ResponseEntity<String> {
        return ResponseEntity.ok("hello World")
    }

    @GetMapping("/test2")
    fun index2(
        @RequestParam param1: Int,
        @RequestParam param2: Int?
    ): String {
        return "$param1${param2}"
    }
}
