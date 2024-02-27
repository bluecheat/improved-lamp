package com.sandbox.kotlinsandbox.mvc.product

import lombok.Builder
import lombok.Data

typealias ProductDtos = Set<ProductDto.Product>

class ProductDto {

    data class CreateProductRequest(var title: String, var owner: String, var description: String)

    @Data
    @Builder
    data class Product(var id: Long, var title: String, var owner: String = "Unknown",  var description: String = "설명없음") {

        // 보조 생성자
        constructor(): this(0, "제목없음")

        // 보조 생성자
        constructor( id: Long): this(id, "제목없음")

    }
}