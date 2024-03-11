package com.sandbox.kotlinsandbox.mvc.user.entity

import com.sandbox.kotlinsandbox.support.jpa.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
class User(
    @Id
    val id: UUID = UUID.randomUUID(),

    val email: String,

    val name: String,

    val password: String,

    val phone: String,
) : BaseEntity()