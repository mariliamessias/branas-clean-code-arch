package com.branas.cleanarch.model

import com.branas.cleanarch.repository.entities.Drivers
import com.branas.cleanarch.repository.entities.Passengers
import org.junit.jupiter.api.*
import java.time.LocalDateTime

class DriversTest {
    @TestFactory
    fun shouldCreateADriverWithAValidDocumentAndEmail() = listOf(
        "83432616074",
        "74587887803",
        "87175659520"
    ).map {
        DynamicTest.dynamicTest("Should create a valid driver") {
            val passengers = Drivers(
                name = "nameTest",
                createdAt = LocalDateTime.now(),
                document = it,
                email = "email@email.com",
                carPlate = "666"
            )
            Assertions.assertEquals(passengers.document, it)
        }
    }

    @Test
    fun shouldCreateADriverThrownExceptionWhenEmailIsNotValid() {
        assertThrows<Exception> {
            Drivers(
                name = "nameTest",
                createdAt = LocalDateTime.now(),
                document = "83432616074",
                email = "invalidemail",
                carPlate = "666"
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
                Passengers(
                    name = "nameTest",
                    createdAt = LocalDateTime.now(),
                    document = it,
                    email = "email@email.com"
                )
            }
        }
    }
}