package com.branas.cleanarch.service

import com.branas.cleanarch.UnitTests
import com.branas.cleanarch.controller.request.CreatePassengersRequest
import com.branas.cleanarch.repository.PassengersRepository
import com.branas.cleanarch.repository.entities.Passengers
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.util.*

class PassengersServiceTest : UnitTests() {
    @InjectMockKs
    lateinit var passengersService: PassengersService

    @MockK
    lateinit var passengersRepository: PassengersRepository

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
        val entityResult = Passengers(
            id = UUID.randomUUID(),
            createdAt = LocalDateTime.now(),
            email = email,
            document = cpf,
            name = name
        )
        every { passengersRepository.save(any()) } returns entityResult
        val result = passengersService.create(request)
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
        every { passengersRepository.save(any()) } throws Exception("error")
        assertThrows<Exception> {
            passengersService.create(request)
        }
    }
}