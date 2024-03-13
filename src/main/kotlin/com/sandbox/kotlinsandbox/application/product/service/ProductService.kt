package com.sandbox.kotlinsandbox.application.product.service

import com.sandbox.kotlinsandbox.application.product.domain.Product
import com.sandbox.kotlinsandbox.application.product.infrastructure.ProductRepository
import com.sandbox.kotlinsandbox.application.user.service.UserViewService
import com.sandbox.kotlinsandbox.web.product.dto.ProductDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    private val userViewService: UserViewService,
    private val productRepository: ProductRepository,
) {

    fun saveProduct(input: ProductDto.CreateRequest, userId: String): ProductDto.Item {
        val user = userViewService.validAndGetUser(userId)
        val newProduct = Product(
            title = input.title,
            description = input.description,
            price = input.price,
            totalCount = input.count,
            count = input.count,
            owner = user,
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

    @Transactional
    fun updateProduct(productId: Long, input: ProductDto.UpdateRequest, userId: String): ProductDto.Item {
        userViewService.validAndGetUser(userId)
        val product = productRepository.findByIdOrNull(productId) ?: throw IllegalArgumentException("해당 상품은 존재하지 않습니다.")

        if (product.owner.id.toString() != userId) {
            throw IllegalArgumentException("해당 상품의 소유자가 아닙니다.")
        }

        product.update(
            newTitle = input.title,
            newDescription = input.description,
            newPrice = input.price
        )

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