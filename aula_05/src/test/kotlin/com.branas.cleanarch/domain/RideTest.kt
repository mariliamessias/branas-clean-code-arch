package com.branas.cleanarch.domain

import com.branas.cleanarch.domain.fare.chainOfResponsability.NormalFareCalculatorHandler
import com.branas.cleanarch.domain.fare.chainOfResponsability.OvernightFareCalculatorHandler
import com.branas.cleanarch.domain.fare.chainOfResponsability.OvernightSundayFareCalculatorHandler
import com.branas.cleanarch.domain.fare.chainOfResponsability.SundayFareCalculatorHandler
import com.branas.cleanarch.domain.ride.Ride
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDateTime

class RideTest {

    @Test
    fun shouldCalculateADayRideWithSuccess() {
        val fareCalculatorHandler = NormalFareCalculatorHandler()
        val ride = Ride(fareCalculator = fareCalculatorHandler, status = "requested")
        ride.addPosition(
            BigDecimal.valueOf(-27.584905257808835),
            BigDecimal.valueOf(-48.545022195325124),
            LocalDateTime.parse("2021-03-01T10:00:00")
        )
        ride.addPosition(
            BigDecimal.valueOf(-27.496887588317275),
            BigDecimal.valueOf(-48.522234807851476),
            LocalDateTime.parse("2021-03-01T10:00:00")
        )
        assertEquals(ride.calculate(), BigDecimal.valueOf(21.0))
    }

    @Test
    fun shouldCalculateANightRideWithSuccess() {
        val fareCalculatorHandler = OvernightFareCalculatorHandler()
        val ride = Ride(fareCalculator = fareCalculatorHandler, status = "requested")
        ride.addPosition(
            BigDecimal.valueOf(-27.584905257808835),
            BigDecimal.valueOf(-48.545022195325124),
            LocalDateTime.parse("2021-03-01T23:00:00")
        )
        ride.addPosition(
            BigDecimal.valueOf(-27.496887588317275),
            BigDecimal.valueOf(-48.522234807851476),
            LocalDateTime.parse("2021-03-01T23:00:00")
        )
        val rideCalculated = ride.calculate()
        assertEquals(rideCalculated, BigDecimal.valueOf(39.0))
    }

    @Test
    fun shouldCalculateASundayDayRideWithSuccess() {
        val fareCalculatorHandler = SundayFareCalculatorHandler()
        val ride = Ride(fareCalculator = fareCalculatorHandler, status = "requested")
        ride.addPosition(
            BigDecimal.valueOf(-27.584905257808835),
            BigDecimal.valueOf(-48.545022195325124),
            LocalDateTime.parse("2021-03-07T10:00:00")
        )
        ride.addPosition(
            BigDecimal.valueOf(-27.496887588317275),
            BigDecimal.valueOf(-48.522234807851476),
            LocalDateTime.parse("2021-03-07T10:00:00")
        )

        val rideCalculated = ride.calculate()
        assertEquals(rideCalculated, BigDecimal.valueOf(29.0))
    }

    @Test
    fun shouldCalculateASundayNightRideWithSuccess() {
        val fareCalculatorHandler = OvernightSundayFareCalculatorHandler()
        val ride = Ride(fareCalculator = fareCalculatorHandler, status = "requested")
        ride.addPosition(
            BigDecimal.valueOf(-27.584905257808835),
            BigDecimal.valueOf(-48.545022195325124),
            LocalDateTime.parse("2021-03-07T23:00:00")
        )
        ride.addPosition(
            BigDecimal.valueOf(-27.496887588317275),
            BigDecimal.valueOf(-48.522234807851476),
            LocalDateTime.parse("2021-03-07T23:00:00")
        )
        val rideCalculated = ride.calculate()
        assertEquals(rideCalculated, BigDecimal.valueOf(50.0))
    }

    @Test
    fun shouldCalculateADayRideWithMinPrice() {
        val fareCalculatorHandler = NormalFareCalculatorHandler()
        val ride = Ride(fareCalculator = fareCalculatorHandler, status = "requested")
        ride.addPosition(
            BigDecimal.valueOf(-27.584905257808835),
            BigDecimal.valueOf(-48.545022195325124),
            LocalDateTime.parse("2023-10-18T10:00:00")
        )
        ride.addPosition(
            BigDecimal.valueOf(-27.579020277800876),
            BigDecimal.valueOf(-48.50838017206791),
            LocalDateTime.parse("2023-10-18T10:00:00")
        )
        val rideCalculated = ride.calculate()
        assertEquals(rideCalculated, BigDecimal.valueOf(10.0))
    }

}