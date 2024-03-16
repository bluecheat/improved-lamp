package com.sandbox.kotlinsandbox.application.auth.service

import com.sandbox.kotlinsandbox.application.user.domain.User
import com.sandbox.kotlinsandbox.application.user.infrastructure.UserRepository
import com.sandbox.kotlinsandbox.web.auth.dto.AuthDto
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val tokenProvider: TokenProvider,
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder,
) {

    @Transactional
    fun join(request: AuthDto.JoinRequest) {

        if (userRepository.existsByEmail(request.email)) {
            throw IllegalArgumentException("이미 존재하는 계정입니다.")
        }

        userRepository.save(
            User(
                email = request.email,
                name = request.name,
                phone = request.phone,
                password = encoder.encode(request.password),
            )
        )
    }

    @Transactional
    fun login(request: AuthDto.LoginRequest): AuthDto.LoginResponse {
        val member = userRepository.findByEmail(request.email)
            ?: throw IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.")

        if (!encoder.matches(request.password, member.password)) {
            throw IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.")
        }

        val token = tokenProvider.createToken("${member.id}:${member.name}")
        return AuthDto.LoginResponse(token)
    }
}