package com.sandbox.kotlinsandbox.mvc.product

import com.sandbox.kotlinsandbox.mvc.product.dto.ProductDto
import com.sandbox.kotlinsandbox.mvc.product.dto.ProductItems
import com.sandbox.kotlinsandbox.mvc.product.service.ProductService
import jakarta.validation.Valid
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productService: ProductService
) {

    @GetMapping("/v1/products")
    fun getProducts(): ProductItems {
        return productService.getProducts()
    }

    @PostMapping("/v1/products")
    fun registerProducts(
        @Valid @RequestBody request: ProductDto.CreateRequest,
        @AuthenticationPrincipal user: User
    ): ProductDto.Item {

        return productService.saveProduct(request, user.username)
    }
}