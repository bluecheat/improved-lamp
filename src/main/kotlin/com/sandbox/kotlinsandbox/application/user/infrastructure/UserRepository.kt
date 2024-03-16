package com.sandbox.kotlinsandbox.application.user.infrastructure

import com.sandbox.kotlinsandbox.application.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {

    fun findByEmail(email: String): User?

    fun existsByEmail(email: String): Boolean
}
