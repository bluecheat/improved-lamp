package com.sandbox.kotlinsandbox.support.error

import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

data class ErrorResponse(
    val code: Int,
    val message: String,
)

@Slf4j
@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException::class)
    fun handleServerException(ex: RuntimeException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(code = HttpStatus.CONFLICT.value(), message = ex.message!!),
            HttpStatus.CONFLICT
        )
    }
}