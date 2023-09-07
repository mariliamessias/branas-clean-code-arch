package com.branas.refactoring.model

import com.branas.refactoring.repository.entities.Passengers
import org.junit.jupiter.api.*
import java.time.LocalDateTime

class PassengersTest {

    @TestFactory
    fun shouldCreateAPassengerWithAValidDocumentAndEmail() = listOf(
        "83432616074",
        "74587887803",
        "87175659520"
    ).map {
        DynamicTest.dynamicTest("Should create a valid passenger") {
            val passengers = Passengers(
                name = "nameTest",
                createdAt = LocalDateTime.now(),
                document = it,
                email = "email@email.com"
            )
            Assertions.assertEquals(passengers.document, it)
        }
    }

    @Test
    fun shouldCreateAPassengerThrownExceptionWhenEmailIsNotValid() {
        assertThrows<Exception> {
            Passengers(
                name = "nameTest",
                createdAt = LocalDateTime.now(),
                document = "83432616074",
                email = "invalidemail"
            )
        }
    }

    @TestFactory
    fun shouldCreateAPassengerThrownExceptionWhenDocumentIsNotValid() = listOf(
        "83432616076",
        "99999999999",
        "834326160",
        ""
    ).map {
        DynamicTest.dynamicTest("Should don't create a valid passenger") {
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