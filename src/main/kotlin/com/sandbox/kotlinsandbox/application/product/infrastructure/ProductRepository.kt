package com.sandbox.kotlinsandbox.application.product.infrastructure

import com.sandbox.kotlinsandbox.application.product.domain.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface ProductRepository : JpaRepository<Product, Long> {


    @Query("select p from Product p inner join fetch p.owner")
    override fun findAll(pageable: Pageable): Page<Product>
}