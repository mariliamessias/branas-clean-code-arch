package com.branas.cleanarch.controller.response

import java.time.LocalDateTime
import java.util.*

data class GetPassengerResponse(
    val id: UUID,
    val name: String,
    val email: String,
    val document: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null
)