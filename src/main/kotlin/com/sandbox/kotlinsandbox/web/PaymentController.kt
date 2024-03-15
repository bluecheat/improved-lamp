package com.sandbox.kotlinsandbox.web

import com.sandbox.kotlinsandbox.application.payment.service.PaymentService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PaymentController(
    private val paymentService: PaymentService
) {

    @PostMapping("/v1/payment/product/{id}")
    fun buy(
        @PathVariable("id") productId: Long,
        @AuthenticationPrincipal user: User
    ) = paymentService.buy(productId, user.username)

}