package com.branas.cleanarch.application.usecase

import com.branas.cleanarch.UnitTests
import com.branas.cleanarch.application.repository.PassengerRepository
import com.branas.cleanarch.application.repository.entities.PassengerEntity
import com.branas.cleanarch.controller.request.CreatePassengersRequest
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.util.*

class CreatePassengerTest : UnitTests() {
    @InjectMockKs
    lateinit var createPassenger: CreatePassenger

    @MockK
    lateinit var passengerRepository: PassengerRepository

    @Test
    fun shouldCreatePassengerWithSuccess() {
        val email = "email@email.com"
        val cpf = "85101946028"
        val name = "name"
        val request = CreatePassengersRequest(
            name = name,
            email = email,
            document = cpf
        )
        val entityResult = PassengerEntity(
            id = UUID.randomUUID(),
            createdAt = LocalDateTime.now(),
            email = email,
            document = cpf,
            name = name
        )
        every { passengerRepository.save(any()) } returns entityResult
        val result = createPassenger.execute(request)
        Assertions.assertNotNull(result)
    }

    @Test
    fun shouldCreatePassengerFailure() {
        val email = "email@email.com"
        val document = "555"
        val name = "name"
        val request = CreatePassengersRequest(
            name = name,
            email = email,
            document = document
        )
        every { passengerRepository.save(any()) } throws Exception("error")
        assertThrows<Exception> {
            createPassenger.execute(request)
        }
    }
}