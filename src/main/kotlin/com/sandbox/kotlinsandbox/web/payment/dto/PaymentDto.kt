package com.sandbox.kotlinsandbox.web.payment.dto

import java.time.LocalDateTime

class PaymentDto {

    data class Item(
        val id: Long,
        val title: String,
        val description: String,
        val owner: String,
        val price: Int,
        val createdDate: LocalDateTime,
        val updatedAt: LocalDateTime,
    )
}