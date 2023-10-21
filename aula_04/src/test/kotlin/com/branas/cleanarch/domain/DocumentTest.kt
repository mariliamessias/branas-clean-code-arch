package com.branas.cleanarch.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertThrows

class DocumentTest {

    @TestFactory
    fun shouldCreateADocumentWithSuccess() = listOf(
        "83432616074",
        "74587887803",
        "87175659520"
    ).map {
        DynamicTest.dynamicTest("Should create a valid driver") {
            val document = Document(value = it)
            Assertions.assertEquals(document.value, it)
        }
    }

    @TestFactory
    fun shouldCreateADocumentThrownExceptionWhenDocumentIsNotValid() = listOf(
        "83432616076",
        "99999999999",
        "834326160",
        ""
    ).map {
        DynamicTest.dynamicTest("Should don't create a valid email") {
            assertThrows<Exception> {
                Document(value = it)
            }
        }
    }
}