package com.branas.cleanarch.application.usecase

import com.branas.cleanarch.UnitTests
import com.branas.cleanarch.infra.request.RideCalculatorRequest
import com.branas.cleanarch.infra.request.RidePositionRequest
import io.mockk.impl.annotations.InjectMockKs
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class CalculateRideTest : UnitTests() {


    @InjectMockKs
    lateinit var calculateRide: CalculateRide

    @Test
    fun shouldCalculateRideWithSuccess() {
        val request = RideCalculatorRequest(
            positions = listOf(
                RidePositionRequest(
                    BigDecimal.valueOf(-27.584905257808835),
                    BigDecimal.valueOf(-48.545022195325124),
                    date = "2021-03-01T10:00:00"
                ),
                RidePositionRequest(
                    BigDecimal.valueOf(-27.496887588317275),
                    BigDecimal.valueOf(-48.522234807851476),
                    date = "2021-03-01T10:00:00"
                )
            )
        )
        val rideCalculated = calculateRide.execute(request)
        Assertions.assertNotNull(rideCalculated)
        Assertions.assertEquals(rideCalculated.price, BigDecimal.valueOf(21.0))
    }
}