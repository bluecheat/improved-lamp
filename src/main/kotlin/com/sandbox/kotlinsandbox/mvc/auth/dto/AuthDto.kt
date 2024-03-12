package com.sandbox.kotlinsandbox.mvc.auth.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

class AuthDto {

    data class JoinRequest(
        @field:Email
        @field:NotBlank(message = "이메일은 공백일 수 없습니다.")
        val email: String,

        @field:NotBlank
        val name: String,

        @field:NotBlank
        val password: String,

        @field:NotBlank
        val phone: String,
    )

    data class LoginRequest(
        @field:Email
        @field:NotBlank(message = "이메일은 공백일 수 없습니다.")
        val email: String,

        @field:NotBlank(message = "비밀번호는 공백일 수 없습니다.")
        val password: String,
    )

    data class LoginResponse(
        val token: String,
    )
}