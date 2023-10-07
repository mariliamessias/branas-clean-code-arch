package com.branas.adapters.service

import com.branas.adapters.UnitTests
import com.branas.adapters.controller.request.CreateDriversRequest
import com.branas.adapters.repository.DriversRepository
import com.branas.adapters.repository.entities.Drivers
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.util.*

class DriversServiceTest : UnitTests() {

    @InjectMockKs
    lateinit var driversService: DriversService

    @MockK
    lateinit var driverRepository: DriversRepository

    @Test
    fun shouldCreatePassengerWithSuccess() {
        val email = "email@email.com"
        val cpf = "85101946028"
        val name = "name"
        val carPlate = "666"
        val request = CreateDriversRequest(
            name = name,
            email = email,
            document = cpf,
            carPlate = carPlate
        )
        val entityResult = Drivers(
            id = UUID.randomUUID(),
            createdAt = LocalDateTime.now(),
            email = email,
            document = cpf,
            name = name,
            carPlate = carPlate
        )
        every { driverRepository.save(any()) } returns entityResult
        val result = driversService.create(request)
        Assertions.assertNotNull(result)
    }

    @Test
    fun shouldCreatePassengerFailure() {
        val email = "email@email.com"
        val document = "555"
        val name = "name"
        val carPlate = "666"
        val request = CreateDriversRequest(
            name = name,
            email = email,
            document = document,
            carPlate = carPlate
        )
        every { driverRepository.save(any()) } throws Exception("error")
        assertThrows<Exception> {
            driversService.create(request)
        }
    }
}