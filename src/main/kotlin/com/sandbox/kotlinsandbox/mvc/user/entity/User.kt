package com.sandbox.kotlinsandbox.mvc.user.entity

import com.sandbox.kotlinsandbox.support.jpa.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity
class User(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    val id: UUID = UUID.randomUUID(),

    val email: String,

    val name: String,

    val password: String,

    val phone: String,
) : BaseEntity()