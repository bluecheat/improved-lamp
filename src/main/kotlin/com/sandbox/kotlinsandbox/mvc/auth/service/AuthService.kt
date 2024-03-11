package com.sandbox.kotlinsandbox.mvc.auth.service

import com.sandbox.kotlinsandbox.mvc.auth.dto.AuthDto
import com.sandbox.kotlinsandbox.mvc.user.entity.User
import com.sandbox.kotlinsandbox.mvc.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder,
) {

    @Transactional
    fun join(request: AuthDto.JoinRequest) {

        if (userRepository.existsByEmail(request.email)) {
            throw IllegalArgumentException("이미 존재하는 계정입니다.")
        }

        userRepository.save(User(
            email = request.email,
            name = request.name,
            phone =  request.phone,
            password = encoder.encode(request.password),
        ))
    }
}