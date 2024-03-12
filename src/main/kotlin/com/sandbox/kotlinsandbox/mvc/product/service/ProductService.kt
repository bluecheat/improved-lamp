package com.sandbox.kotlinsandbox.mvc.product.service

import com.sandbox.kotlinsandbox.mvc.product.dto.ProductDto
import com.sandbox.kotlinsandbox.mvc.product.dto.ProductItems
import com.sandbox.kotlinsandbox.mvc.product.entity.Product
import com.sandbox.kotlinsandbox.mvc.product.repository.ProductRepository
import com.sandbox.kotlinsandbox.mvc.user.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val userRepository: UserRepository,
) {

    fun getProducts(): ProductItems {
        return setOf()
    }

    fun saveProduct(input: ProductDto.CreateRequest, userId: String): ProductDto.Item {

        val user = userRepository.findById(UUID.fromString(userId)).takeIf { it.isPresent }
            ?: throw IllegalArgumentException("해당 유저는 존재하지 않습니다.")

        val newProduct = Product(
            title = input.title,
            description = input.description,
            price = input.price,
            totalCount = input.count,
            count = input.count,
            owner = user.get(),
        )
        val product = productRepository.save(newProduct)

        return ProductDto.Item(
            id = product.id,
            title = product.title,
            description = product.description,
            owner = "${product.owner.id}",
            price = product.price,
            createdDate = product.createdAt,
            updatedAt = product.updatedAt,
        )
    }
}