package com.sandbox.kotlinsandbox.application.product.infrastructure

import com.sandbox.kotlinsandbox.application.product.domain.Product
import com.sandbox.kotlinsandbox.application.product.service.out.ProductRepositoryPort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class ProductRdbRepository(
    val productRepository: ProductRepository,
) : ProductRepositoryPort {

    override fun findAllBy(pageable: Pageable, title: String?, owner: String?): Page<Product> {
        return productRepository.findAllBy(pageable, title, owner)
    }

    override fun getProduct(id: Long): Product? {
        return productRepository.findByIdOrNull(id)
    }
}