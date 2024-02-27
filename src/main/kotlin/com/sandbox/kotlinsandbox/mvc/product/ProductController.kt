package com.sandbox.kotlinsandbox.mvc.product

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productService: ProductService
) {

    @GetMapping("/v1/products")
    fun getProducts(): ProductDtos {
        return productService.getProducts()
    }

    @PostMapping("/v1/products")
    fun registerProducts(productDto: ProductDto.CreateProductRequest): ProductDto.Product {
        return productService.saveProduct(productDto)
    }
}