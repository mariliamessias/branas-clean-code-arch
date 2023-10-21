package com.branas.cleanarch.application.usecase

import com.branas.cleanarch.UnitTests
import com.branas.cleanarch.application.repository.DriverRepository
import com.branas.cleanarch.application.repository.entities.DriverEntity
import com.branas.cleanarch.controller.request.CreateDriversRequest
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.util.*

class CreateDriverTest : UnitTests() {

    @InjectMockKs
    lateinit var createDriver: CreateDriver

    @MockK
    lateinit var driverRepository: DriverRepository

    @Test
    fun shouldCreatePassengerWithSuccess() {
        val email = "email@email.com"
        val cpf = "85101946028"
        val name = "name"
        val carPlate = "AAA9999"
        val request = CreateDriversRequest(
            name = name,
            email = email,
            document = cpf,
            carPlate = carPlate
        )
        val entityResult = DriverEntity(
            id = UUID.randomUUID(),
            createdAt = LocalDateTime.now(),
            email = email,
            document = cpf,
            name = name,
            carPlate = carPlate
        )
        every { driverRepository.save(any()) } returns entityResult
        val result = createDriver.execute(request)
        Assertions.assertNotNull(result)
    }

    @Test
    fun shouldCreatePassengerFailure() {
        val email = "email@email.com"
        val document = "555"
        val name = "name"
        val carPlate = "AAA9999"
        val request = CreateDriversRequest(
            name = name,
            email = email,
            document = document,
            carPlate = carPlate
        )
        every { driverRepository.save(any()) } throws Exception("error")
        assertThrows<Exception> {
            createDriver.execute(request)
        }
    }
}