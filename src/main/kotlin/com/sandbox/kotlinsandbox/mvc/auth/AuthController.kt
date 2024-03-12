package com.sandbox.kotlinsandbox.mvc.auth

import com.sandbox.kotlinsandbox.mvc.auth.dto.AuthDto
import com.sandbox.kotlinsandbox.mvc.auth.service.AuthService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authService: AuthService,
) {

    @PostMapping("/v1/auth/join")
    fun join(@Valid @RequestBody request: AuthDto.JoinRequest) = authService.join(request)

    @PostMapping("/v1/auth/login")
    fun login(@Valid @RequestBody request: AuthDto.LoginRequest) = authService.login(request)
}