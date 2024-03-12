package com.sandbox.kotlinsandbox.mvc.product.dto

import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

typealias ProductItems = Set<ProductDto.Item>

class ProductDto {

    data class CreateRequest(
        @field:NotBlank(message = "상품 제목은 공백일 수 없습니다.")
        val title: String,
        val description: String,
        val price: Int,
        val count: Int
    )

    data class Item(
        val id: Long,
        val title: String,
        val description: String,
        val owner: String,
        val price: Int,
        val createdDate: LocalDateTime,
        val updatedAt: LocalDateTime,
    )
}