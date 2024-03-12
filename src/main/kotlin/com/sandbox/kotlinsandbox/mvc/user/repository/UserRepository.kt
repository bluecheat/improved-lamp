package com.sandbox.kotlinsandbox.mvc.user.repository

import com.sandbox.kotlinsandbox.mvc.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {

    fun findByEmail(email: String): User

    fun existsByEmail(email: String): Boolean
}
