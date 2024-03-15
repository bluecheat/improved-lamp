package com.sandbox.kotlinsandbox.application.payment.infrastructure

import com.sandbox.kotlinsandbox.application.payment.domain.Payment
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PaymentRepository : JpaRepository<Payment, UUID> {


}