package com.branas.cleanarch.domain

import com.branas.cleanarch.domain.driver.CarPlate
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CarPlateTest {

    @Test
    fun shouldValidateCarPlateWithSuccess() {
        val carPlate = CarPlate(value = "AAA9999")
        Assertions.assertNotNull(carPlate)
    }

    @Test
    fun shouldValidateCarPlateWithInvalidValueThrownException() {
        assertThrows<Exception> {
            CarPlate(value = "AAA999")
        }
    }
}