package com.branas.cleanarch.application.usecase

import com.branas.cleanarch.UnitTests
import com.branas.cleanarch.application.repository.PassengerRepository
import com.branas.cleanarch.application.repository.entities.PassengerEntity
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.util.*

class GetPassengerTest : UnitTests() {
    @InjectMockKs
    lateinit var getPassenger: GetPassenger

    @MockK
    lateinit var passengerRepository: PassengerRepository

    @Test
    fun shouldCreatePassengerWithSuccess() {
        val email = "email@email.com"
        val cpf = "85101946028"
        val name = "name"
        val id = UUID.randomUUID()
        val entityResult = PassengerEntity(
            id = id,
            createdAt = LocalDateTime.now(),
            email = email,
            document = cpf,
            name = name
        )
        every { passengerRepository.getReferenceById(any()) } returns entityResult
        val passenger = getPassenger.execute(id.toString())
        Assertions.assertNotNull(passenger)
        Assertions.assertEquals(passenger.id, id)
        Assertions.assertEquals(passenger.createdAt, entityResult.createdAt)
        Assertions.assertEquals(passenger.name, name)
        Assertions.assertEquals(passenger.document, cpf)
        Assertions.assertEquals(passenger.email, email)
    }

    @Test
    fun shouldCreatePassengerFailure() {
        val id = UUID.randomUUID()
        every { passengerRepository.getReferenceById(any()) } throws Exception("error")
        assertThrows<Exception> {
            getPassenger.execute(id.toString())
        }
    }
}