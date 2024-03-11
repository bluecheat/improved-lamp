package com.sandbox.kotlinsandbox.mvc.product.services

import com.sandbox.kotlinsandbox.mvc.product.dto.ProductDto
import com.sandbox.kotlinsandbox.mvc.product.dto.ProductItems
import com.sandbox.kotlinsandbox.mvc.product.entity.Product
import com.sandbox.kotlinsandbox.mvc.product.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {

    fun getProducts(): ProductItems {
        return setOf()
    }

    fun saveProduct(input: ProductDto.CreateRequest): ProductDto.Item {
        val newProduct = Product(
            title = input.title,
            description = input.description,
            owner = input.owner,
            price = input.price
        )
        val product = productRepository.save(newProduct)

        return ProductDto.Item(
            id = product.id,
            title = product.title,
            description = product.description,
            owner = product.owner,
            price = product.price,
            createdDate = product.createdAt,
            updatedAt = product.updatedAt,
        )
    }
}