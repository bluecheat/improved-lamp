package com.sandbox.kotlinsandbox.mvc.product

import org.springframework.stereotype.Service

@Service
class ProductService {

    fun getProducts(): ProductDtos {
        return setOf(
            ProductDto.Product(id = 1),
            ProductDto.Product(id = 2, title = "로션"),
            ProductDto.Product(id = 3, title = "핸드크림", owner = "꽃을든남자"),
            ProductDto.Product(id = 4, title = "로션"),
        )
    }

    fun saveProduct(product: ProductDto.CreateProductRequest): ProductDto.Product {
        return ProductDto.Product(id = 2, title = "신규 제품")
    }

}