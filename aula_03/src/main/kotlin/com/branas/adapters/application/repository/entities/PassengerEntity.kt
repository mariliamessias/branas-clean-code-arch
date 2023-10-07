package com.branas.adapters.application.repository.entities

import com.branas.adapters.configuration.NoArgs
import com.branas.adapters.domain.Document
import com.branas.adapters.domain.Email
import com.branas.adapters.domain.Passenger
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table
@NoArgs
class PassengerEntity(
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

    @Column
    val createdAt: LocalDateTime,

    @Column
    val updatedAt: LocalDateTime? = null
) {
    fun toDomain() = Passenger(
        id = id,
        name = name,
        email = Email(email),
        document = Document(document),
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}