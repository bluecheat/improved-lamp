package com.sandbox.kotlinsandbox.mvc.product

import com.sandbox.kotlinsandbox.mvc.product.dto.ProductDto
import com.sandbox.kotlinsandbox.mvc.product.service.ProductService
import com.sandbox.kotlinsandbox.mvc.product.service.ProductViewService
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(
    private val productService: ProductService,
    private val productViewService: ProductViewService,
) {

    @GetMapping("/v1/products")
    fun getProducts(
        @PageableDefault(
            page = 1, size = 10, sort = ["id"], direction = Sort.Direction.DESC
        )
        pageable: Pageable
    ) = productViewService.getProducts(pageable)

    @GetMapping("/v1/products/{id}")
    fun updateProducts(
        @PathVariable("id") productId: Long,
    ) = productViewService.getProduct(productId)

    @PostMapping("/v1/products")
    fun registerProducts(
        @Valid @RequestBody request: ProductDto.CreateRequest,
        @AuthenticationPrincipal user: User
    ) = productService.saveProduct(request, user.username)

    @PutMapping("/v1/products/{id}")
    fun updateProducts(
        @PathVariable("id") productId: Long,
        @Valid @RequestBody request: ProductDto.UpdateRequest,
        @AuthenticationPrincipal user: User
    ) = productService.updateProduct(productId, request, user.username)
}