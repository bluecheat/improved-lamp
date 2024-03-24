package com.sandbox.kotlinsandbox.application.product.port.`in`

import com.sandbox.kotlinsandbox.web.product.dto.ProductDto
import com.sandbox.kotlinsandbox.web.product.dto.ProductItems
import org.springframework.data.domain.Pageable

interface ProductViewPort {
    fun getProduct(productId: Long): ProductDto.Item
    fun getProducts(pageable: Pageable, condition: ProductDto.SearchRequest?): ProductItems
}