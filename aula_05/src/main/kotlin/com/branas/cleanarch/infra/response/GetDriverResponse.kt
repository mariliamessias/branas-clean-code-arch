package com.branas.cleanarch.infra.response

import java.time.LocalDateTime
import java.util.*

data class GetDriverResponse(
    val id: UUID,
    val name: String,
    val email: String,
    val document: String,
    val carPlate: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null
)