package com.sandbox.kotlinsandbox.web

import com.sandbox.kotlinsandbox.application.auth.service.AuthService
import com.sandbox.kotlinsandbox.web.auth.dto.AuthDto
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authService: AuthService,
) {

    @PostMapping("/v1/auth/join")
    fun join(@Validated @RequestBody request: AuthDto.JoinRequest) = authService.join(request)

    @PostMapping("/v1/auth/login")
    fun login(@Validated @RequestBody request: AuthDto.LoginRequest) = authService.login(request)
}