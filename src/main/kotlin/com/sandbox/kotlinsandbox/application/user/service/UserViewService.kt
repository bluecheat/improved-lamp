package com.sandbox.kotlinsandbox.application.user.service

import com.sandbox.kotlinsandbox.application.user.domain.User
import com.sandbox.kotlinsandbox.application.user.infrastructure.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserViewService(
    private val userRepository: UserRepository,
) {

    internal fun getUser(userId: String): User? {
        return userRepository.findByIdOrNull(UUID.fromString(userId))
    }

    internal fun validAndGetUser(userId: String): User {
        val user = getUser(userId) ?: throw IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        return user
    }
}