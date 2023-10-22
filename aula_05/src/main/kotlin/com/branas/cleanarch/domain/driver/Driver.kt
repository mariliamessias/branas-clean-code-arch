package com.branas.cleanarch.domain.driver

import com.branas.cleanarch.application.repository.entities.DriverEntity
import com.branas.cleanarch.domain.Document
import com.branas.cleanarch.domain.Email
import java.time.LocalDateTime
import java.util.*

data class Driver(
    val id: UUID? = null,
    var name: String,
    var email: Email,
    var document: Document,
    var carPlate: CarPlate,
    var createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null
) {
    fun toEntity() = DriverEntity(
        name = name,
        carPlate = carPlate.value,
        document = document.value,
        email = email.value,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )

    companion object {
        fun create(name: String, email: String, carPlate: String, document: String, createdAt: LocalDateTime) = Driver(
            name = name,
            email = Email(email),
            carPlate = CarPlate(carPlate),
            document = Document(document),
            createdAt = createdAt
        )
    }
}