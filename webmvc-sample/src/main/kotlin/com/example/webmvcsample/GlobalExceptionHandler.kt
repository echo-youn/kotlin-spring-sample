package com.example.webmvcsample

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(
        Exception::class,
        RuntimeException::class,
    )
    fun exceptionHandle(): ResponseEntity<String> {
        return ResponseEntity.internalServerError().body("something went wrong")
    }
}
