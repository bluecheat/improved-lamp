package com.sandbox.kotlinsandbox.mvc.auth.dto

class AuthDto {

    data class JoinRequest(
        val email: String,
        val name: String,
        val password: String,
        val phone: String,
    )

    data class LoginRequest(
        val email: String,
        val password: String,
    )
}