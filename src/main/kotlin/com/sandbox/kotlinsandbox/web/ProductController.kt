package com.sandbox.kotlinsandbox.web

import com.sandbox.kotlinsandbox.application.product.port.`in`.ProductCommandPort
import com.sandbox.kotlinsandbox.application.product.port.`in`.ProductViewPort
import com.sandbox.kotlinsandbox.web.product.dto.ProductDto
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(
    private val productService: ProductCommandPort,
    private val productViewService: ProductViewPort,
) {

    @GetMapping("/v1/products")
    fun getProducts(
        @PageableDefault(
            page = 0, size = 10, sort = ["id"], direction = Sort.Direction.DESC,
        ) pageable: Pageable,
        @RequestParam(required = false, name = "title") title: String?,
        @RequestParam(required = false, name = "owner") owner: String?,
    ) = productViewService.getProducts(pageable, ProductDto.SearchRequest(title, owner))

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