package com.sandbox.kotlinsandbox.mvc

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController

@GetMapping("/v1/products")
fun getProducts() {

}