package com.sandbox.kotlinsandbox.application.product.infrastructure

import com.sandbox.kotlinsandbox.application.product.domain.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProductRepositoryCustom {

    fun findAllBy(pageable: Pageable, title: String? = "", owner: String? = ""): Page<Product>
}