package com.sandbox.kotlinsandbox.support.error

import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
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

    // @RequestBody의 파라미터가 유효하지 않으면 MethodArgumentNotValidException 에러가 발생합니다
    @ExceptionHandler
    fun handleInvalidRequestBodyException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val allErrors = e.bindingResult.allErrors.map { it.defaultMessage }
        val statusCode = HttpStatus.BAD_REQUEST
        val exceptionResult = ErrorResponse(statusCode.value(), allErrors.first() ?: "에러")

        return ResponseEntity(exceptionResult, statusCode)
    }
}