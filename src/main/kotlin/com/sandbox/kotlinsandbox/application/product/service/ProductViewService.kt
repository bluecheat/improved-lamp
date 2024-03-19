package com.sandbox.kotlinsandbox.application.product.service

import com.sandbox.kotlinsandbox.application.product.service.`in`.ProductViewPort
import com.sandbox.kotlinsandbox.application.product.service.out.ProductRepositoryPort
import com.sandbox.kotlinsandbox.web.product.dto.ProductDto
import com.sandbox.kotlinsandbox.web.product.dto.ProductItems
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductViewService(
    private val productRepository: ProductRepositoryPort,
) : ProductViewPort {

    @Transactional(readOnly = true)
    override fun getProducts(pageable: Pageable, condition: ProductDto.SearchRequest?): ProductItems {
        return productRepository.findAllBy(pageable, title = condition?.title, owner = condition?.owner).map {
            ProductDto.Item(
                id = it.id,
                title = it.title,
                description = it.description,
                owner = "${it.owner.id}",
                price = it.price,
                createdDate = it.createdAt,
                updatedAt = it.updatedAt,
            )
        }
    }

    @Transactional(readOnly = true)
    override fun getProduct(productId: Long): ProductDto.Item {
        val product = productRepository.getProduct(productId) ?: throw IllegalArgumentException("해당 상품은 존재하지 않습니다.")
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