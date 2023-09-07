package com.branas.refactoring.repository.entities

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table
class Passengers(
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
) : User(name, email, document)