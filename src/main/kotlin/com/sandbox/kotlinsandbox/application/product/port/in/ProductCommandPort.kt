package com.sandbox.kotlinsandbox.application.product.port.`in`

import com.sandbox.kotlinsandbox.web.product.dto.ProductDto

interface ProductCommandPort {
    fun saveProduct(input: ProductDto.CreateRequest, userId: String): ProductDto.Item
    fun updateProduct(productId: Long, input: ProductDto.UpdateRequest, userId: String): ProductDto.Item
}