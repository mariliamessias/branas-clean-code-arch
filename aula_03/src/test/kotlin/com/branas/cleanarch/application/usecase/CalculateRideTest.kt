package com.branas.cleanarch.application.usecase

import com.branas.cleanarch.UnitTests
import com.branas.cleanarch.controller.request.RideCalculatorRequest
import com.branas.cleanarch.controller.request.RideSegmentRequest
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
            segments = listOf(
                RideSegmentRequest(
                    distance = 10,
                    date = "2021-03-01T10:00:00"
                )
            )
        )
        val rideCalculated = calculateRide.execute(request)
        Assertions.assertNotNull(rideCalculated)
        Assertions.assertEquals(rideCalculated.price, BigDecimal.valueOf(21.0))
    }
}