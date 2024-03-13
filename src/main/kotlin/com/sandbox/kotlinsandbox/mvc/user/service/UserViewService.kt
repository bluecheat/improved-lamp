package com.sandbox.kotlinsandbox.mvc.user.service

import com.sandbox.kotlinsandbox.mvc.user.entity.User
import com.sandbox.kotlinsandbox.mvc.user.repository.UserRepository
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