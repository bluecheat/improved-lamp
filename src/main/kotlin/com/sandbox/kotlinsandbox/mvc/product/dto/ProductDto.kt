package com.sandbox.kotlinsandbox.mvc.product.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.data.domain.Page
import java.time.LocalDateTime

typealias ProductItems = Page<ProductDto.Item>

class ProductDto {

    data class CreateRequest(
        @field:NotBlank(message = "상품 제목은 공백일 수 없습니다.")
        val title: String,

        @field:NotBlank(message = "상품 내용은 공백일 수 없습니다.")
        val description: String,

        @field:Min(1_000)
        @field:Max(1_000_000)
        val price: Int,

        @field:Min(1)
        @field:Max(5000)
        val count: Int
    )

    data class UpdateRequest(
        @field:Size(min = 1, message = "상품 제목은 한글자 이상이여야합니다.")
        val title: String?,

        @field:Size(min = 1, message = "상품 내용은 한글자 이상이여야합니다.")
        val description: String?,

        @field:Min(1_000)
        @field:Max(1_000_000)
        val price: Int?
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