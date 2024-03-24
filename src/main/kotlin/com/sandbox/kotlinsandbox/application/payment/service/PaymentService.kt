package com.sandbox.kotlinsandbox.application.payment.service

import com.sandbox.kotlinsandbox.application.payment.domain.Payment
import com.sandbox.kotlinsandbox.application.payment.infrastructure.PaymentRepository
import com.sandbox.kotlinsandbox.application.product.infrastructure.ProductRepository
import com.sandbox.kotlinsandbox.application.user.service.UserViewUseCase
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class PaymentService(
    private val userViewService: UserViewUseCase,
    private val paymentRepository: PaymentRepository,
    private val productRepository: ProductRepository,
) {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun buy(productId: Long, userId: String): Payment {
        val buyer = userViewService.validAndGetUser(userId)
        val product = productRepository.findByIdOrNull(productId) ?: throw IllegalArgumentException("해당 상품은 존재하지 않습니다.")

        if (product.owner.id.toString() == userId) {
            throw IllegalArgumentException("본인의 상품을 결재할 수 없습니다")
        }

        val payment = Payment(
            product = product,
            buyer = buyer,
        )
        paymentRepository.save(payment)
        product.decrease()

        return payment
    }
}