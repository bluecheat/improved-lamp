package com.sandbox.kotlinsandbox.application.user.port.`in`

import com.sandbox.kotlinsandbox.application.user.domain.User

interface UserViewPort {
    fun getUser(userId: String): User?
    fun validAndGetUser(userId: String): User
}