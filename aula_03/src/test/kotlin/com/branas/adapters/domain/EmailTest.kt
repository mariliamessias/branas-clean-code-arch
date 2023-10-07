package com.branas.adapters.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class EmailTest {

    @Test
    fun shouldCreateAEmailValidWithSuccess() {
        val email = Email(value = "john.doe@gmail.com")
        Assertions.assertNotNull(email)
    }

    @Test
    fun shouldCreateAEmailThrownExceptionWhenEmailIsNotValid() {
        assertThrows<Exception> {
            Email(value = "invalidemail")
        }
    }
}