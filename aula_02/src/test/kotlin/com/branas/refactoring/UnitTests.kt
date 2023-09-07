package com.branas.refactoring

import io.mockk.MockKAnnotations
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UnitTests {
    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }
}