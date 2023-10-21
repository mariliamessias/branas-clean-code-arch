package com.branas.cleanarch.domain

import com.branas.cleanarch.application.repository.entities.PassengerEntity
import java.time.LocalDateTime
import java.util.*

data class Passenger(
    val id: UUID? = null,
    val name: String,
    val email: Email,
    val document: Document,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null
) {
    fun toEntity() = PassengerEntity(
        name = name,
        email = email.value,
        document = document.value,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    companion object {
        fun create(name: String, email: String, document: String, createdAt: LocalDateTime) = Passenger(
            name = name,
            email = Email(email),
            document = Document(document),
            createdAt = createdAt
        )
    }
}