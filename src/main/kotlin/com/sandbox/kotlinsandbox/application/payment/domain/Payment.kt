package com.sandbox.kotlinsandbox.application.payment.domain

import com.sandbox.kotlinsandbox.application.product.domain.Product
import com.sandbox.kotlinsandbox.application.user.domain.User
import com.sandbox.kotlinsandbox.support.jpa.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity
class Payment(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    val id: UUID = UUID.randomUUID(),

    @OneToOne
    @JoinColumn
    val buyer: User,

    @ManyToOne
    @JoinColumn
    val product: Product,

    ) : BaseEntity() {
}