package com.sandbox.kotlinsandbox.application.product.port.out

import com.sandbox.kotlinsandbox.application.product.domain.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProductRepositoryPort {
    fun getProduct(id: Long): Product?
    fun findAllBy(pageable: Pageable, title: String? = "", owner: String? = ""): Page<Product>
    fun save(newProduct: Product): Product
    fun findByIdOrNull(id: Long): Product?
}