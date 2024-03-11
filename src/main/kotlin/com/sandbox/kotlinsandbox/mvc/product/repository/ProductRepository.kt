package com.sandbox.kotlinsandbox.mvc.product.repository

import com.sandbox.kotlinsandbox.mvc.product.entity.Product
import org.springframework.data.jpa.repository.JpaRepository


interface ProductRepository: JpaRepository<Product, Long>