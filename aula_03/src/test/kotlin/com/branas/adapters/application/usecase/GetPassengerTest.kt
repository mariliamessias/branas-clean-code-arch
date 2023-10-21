package com.branas.adapters.application.usecase

import com.branas.adapters.UnitTests
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
    lateinit var passengerRepository: com.branas.adapters.application.repository.PassengerRepository

    @Test
    fun shouldCreatePassengerWithSuccess() {
        val email = "email@email.com"
        val cpf = "85101946028"
        val name = "name"
        val id = UUID.randomUUID()
        val entityResult = com.branas.adapters.application.repository.entities.PassengerEntity(
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