package com.branas.cleanarch.domain

import com.branas.cleanarch.domain.driver.Driver
import org.junit.jupiter.api.*
import java.time.LocalDateTime

class DriverTest {
    @TestFactory
    fun shouldCreateADriverWithSuccess() = listOf(
        "83432616074",
        "74587887803",
        "87175659520"
    ).map {
        DynamicTest.dynamicTest("Should create a valid driver") {
            val passengers = Driver.create(
                name = "nameTest",
                createdAt = LocalDateTime.now(),
                document = it,
                email = "email@email.com",
                carPlate = "AAA9999"
            )
            Assertions.assertEquals(passengers.document.value, it)
        }
    }

    @Test
    fun shouldCreateADriverThrownExceptionWhenEmailIsNotValid() {
        assertThrows<Exception> {
            Driver.create(
                name = "nameTest",
                createdAt = LocalDateTime.now(),
                document = "83432616074",
                email = "invalidemail",
                carPlate = "AAA9999"
            )
        }
    }

    @TestFactory
    fun shouldCreateADriverThrownExceptionWhenDocumentIsNotValid() = listOf(
        "83432616076",
        "99999999999",
        "834326160",
        ""
    ).map {
        DynamicTest.dynamicTest("Should don't create a valid driver") {
            assertThrows<Exception> {
                Driver.create(
                    name = "nameTest",
                    createdAt = LocalDateTime.now(),
                    document = it,
                    email = "email@email.com",
                    carPlate = "AAA9999"
                )
            }
        }
    }
}