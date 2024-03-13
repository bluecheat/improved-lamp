package com.sandbox.kotlinsandbox.application.product.domain

import com.sandbox.kotlinsandbox.application.user.domain.User
import com.sandbox.kotlinsandbox.support.jpa.BaseEntity
import jakarta.persistence.*

@Entity
class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(length = 150, nullable = false)
    var title: String,

    @Column(length = 500, nullable = false)
    var description: String,

    @ManyToOne
    @JoinColumn
    val owner: User,

    var price: Int,

    var count: Int,

    var totalCount: Int,

    ) : BaseEntity() {

    fun decrease() {
        count--
    }

    fun update(newTitle: String?, newDescription: String?, newPrice: Int?) {
        newTitle?.let {
            title = it
        }

        newDescription?.let {
            description = it
        }

        newPrice?.let {
            price = it
        }
    }
}