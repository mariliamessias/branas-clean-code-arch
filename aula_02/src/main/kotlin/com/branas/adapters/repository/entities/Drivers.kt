package com.branas.adapters.repository.entities

import com.branas.adapters.configuration.NoArgs
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@com.branas.adapters.configuration.NoArgs
@Table
class Drivers(
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
) : User(name, email, document)