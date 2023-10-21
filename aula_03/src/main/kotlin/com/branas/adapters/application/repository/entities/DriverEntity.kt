package com.branas.adapters.application.repository.entities

import com.branas.adapters.configuration.NoArgs
import com.branas.adapters.domain.CarPlate
import com.branas.adapters.domain.Document
import com.branas.adapters.domain.Driver
import com.branas.adapters.domain.Email
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table
@NoArgs
class DriverEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    val id: UUID? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val document: String,

    @Column(nullable = false)
    val carPlate: String,

    @Column
    val createdAt: LocalDateTime,

    @Column
    val updatedAt: LocalDateTime? = null
) {
    fun toDomain() = Driver(
        id = id,
        name = name,
        email = Email(email),
        document = Document(document),
        carPlate = CarPlate(carPlate),
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}