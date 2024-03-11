package com.sandbox.kotlinsandbox.mvc.user.repository

import com.sandbox.kotlinsandbox.mvc.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): User

    fun existsByEmail(email: String): Boolean
}
