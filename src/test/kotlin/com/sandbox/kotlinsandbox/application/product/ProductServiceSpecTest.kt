package com.sandbox.kotlinsandbox.application.product

import com.sandbox.kotlinsandbox.application.product.domain.Product
import com.sandbox.kotlinsandbox.application.product.service.ProductViewService
import com.sandbox.kotlinsandbox.application.product.service.out.ProductRepositoryPort
import com.sandbox.kotlinsandbox.application.user.domain.User
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.mockk
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest

val productRepositoryPort = mockk<ProductRepositoryPort>()

@InjectMockKs
val productViewPort = ProductViewService(productRepositoryPort)

internal class ProductServiceSpecTest(

) : BehaviorSpec() {

    init {
        given("상품이 여럿 존재하고 있을때") {
            val testProduct = Product(
                id = 1,
                title = "testProduct",
                description = "testProductDescription",
                price = 0,
                count = 0,
                totalCount = 0,
                owner = User(email = "test", name = "test", phone = "-", password = "test")
            )

            every { productRepositoryPort.findAllBy(any(), any(), any()) } returns PageImpl(
                listOf(testProduct),
                PageRequest.of(0, 10),
                1
            )

            `when`("전체 조회 시") {
                val products = productViewPort.getProducts(pageable = PageRequest.of(0, 10), condition = null)
                then("목록을 응답한다.") {
                    products.shouldNotBeNull() // 결과가 null이 아닌지 확인
                    products.content.shouldHaveSize(1) // 결과 목록의 크기가 1인지 확인

                    val retrievedProduct = products.content[0] // 결과 목록에서 첫 번째 Product 객체를 가져옴
                    retrievedProduct.id shouldBe testProduct.id
                    retrievedProduct.title shouldBe testProduct.title
                    retrievedProduct.description shouldBe testProduct.description
                    retrievedProduct.price shouldBe testProduct.price
                }
            }
        }

    }
}