package com.sandbox.kotlinsandbox.application.product.service.out

import com.sandbox.kotlinsandbox.application.product.domain.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProductRepositoryPort {
    fun findAllBy(pageable: Pageable, title: String? = "", owner: String? = ""): Page<Product>
    fun getProduct(id: Long): Product?
}