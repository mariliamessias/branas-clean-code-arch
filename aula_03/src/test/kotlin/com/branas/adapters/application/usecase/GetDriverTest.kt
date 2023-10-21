package com.branas.adapters.application.usecase

import com.branas.adapters.UnitTests
import com.branas.adapters.application.repository.DriverRepository
import com.branas.adapters.application.repository.entities.DriverEntity
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.util.*

class GetDriverTest : UnitTests() {

    @InjectMockKs
    lateinit var getDriver: GetDriver

    @MockK
    lateinit var driverRepository: DriverRepository

    @Test
    fun shouldGetPassengerWithSuccess() {
        val email = "email@email.com"
        val cpf = "85101946028"
        val name = "name"
        val carPlate = "AAA9999"
        val id = UUID.randomUUID()
        val entityResult = DriverEntity(
            id = id,
            createdAt = LocalDateTime.now(),
            email = email,
            document = cpf,
            name = name,
            carPlate = carPlate
        )
        every { driverRepository.getReferenceById(any()) } returns entityResult
        val driver = getDriver.execute(id.toString())
        Assertions.assertNotNull(driver)
        Assertions.assertEquals(driver.id, id)
        Assertions.assertEquals(driver.createdAt, entityResult.createdAt)
        Assertions.assertEquals(driver.name, name)
        Assertions.assertEquals(driver.document, cpf)
        Assertions.assertEquals(driver.email, email)
        Assertions.assertEquals(driver.carPlate, carPlate)
    }

    @Test
    fun shouldGetPassengerFailure() {
        val id = UUID.randomUUID()
        every { driverRepository.getReferenceById(any()) } throws Exception("error")
        assertThrows<Exception> {
            getDriver.execute(id.toString())
        }
    }
}