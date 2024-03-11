package com.sandbox.kotlinsandbox.mvc.product.entity

import com.sandbox.kotlinsandbox.support.jpa.BaseEntity
import jakarta.persistence.*

@Entity
class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(length = 150, nullable = false)
    val title: String,

    @Column(length = 500, nullable = false)
    val description: String,

    val owner: String,

    val price: Int,

    val count: Int,

    val totalCount: Int,

    ) : BaseEntity() {

}