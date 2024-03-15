package com.sandbox.kotlinsandbox.application.product.service

import com.sandbox.kotlinsandbox.web.product.dto.ProductDto
import com.sandbox.kotlinsandbox.web.product.dto.ProductItems
import org.springframework.data.domain.Pageable

interface IGetterProduct {
    fun getProduct(productId: Long): ProductDto.Item
    fun getProducts(pageable: Pageable, condition: ProductDto.SearchRequest): ProductItems
}