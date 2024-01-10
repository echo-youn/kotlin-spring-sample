package com.example.hexarch.layered.config

import org.apache.catalina.connector.ClientAbortException
import org.hibernate.exception.ConstraintViolationException
import org.springframework.core.annotation.Order
import org.springframework.http.InvalidMediaTypeException
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpMediaTypeNotAcceptableException
import org.springframework.web.bind.MissingRequestHeaderException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.net.SocketException

// Order 정해줘야할까?
// @Order(0)
@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<String> {
        println("logging")

        return ResponseEntity.internalServerError().build()
    }

    @ExceptionHandler(
        value = [
            ConstraintViolationException::class,
            MissingRequestHeaderException::class,
            MethodArgumentTypeMismatchException::class,
            MissingServletRequestParameterException::class,
            ClientAbortException::class,
            IllegalArgumentException::class,
            HttpMediaTypeNotAcceptableException::class,
            InvalidMediaTypeException::class,
        ]
    )
    fun validException(e: Exception): ResponseEntity<String> {
        println("logging2")
        return ResponseEntity.internalServerError().build()
    }
}
