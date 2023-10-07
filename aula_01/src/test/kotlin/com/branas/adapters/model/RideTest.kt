package com.branas.adapters.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.time.LocalDateTime

class RideTest {

    @Test
    fun shouldCalculateADayRideWithSuccess() {
        val segments = mutableListOf(
            Segment(
                distance = BigDecimal.valueOf(10),
                date = LocalDateTime.parse("2021-03-01T10:00:00")
            )
        )
        val rideCalculated = Ride(segments).calculate()
        assertEquals(rideCalculated, BigDecimal.valueOf(21.0))
    }

    @Test
    fun shouldCalculateANightRideWithSuccess() {
        val segments = mutableListOf(
            Segment(
                distance = BigDecimal.valueOf(10),
                date = LocalDateTime.parse("2021-03-01T23:00:00")
            )
        )
        val rideCalculated = Ride(segments).calculate()
        assertEquals(rideCalculated, BigDecimal.valueOf(39.0))
    }

    @Test
    fun shouldCalculateASundayDayRideWithSuccess() {
        val segments = mutableListOf(
            Segment(
                distance = BigDecimal.valueOf(10),
                date = LocalDateTime.parse("2021-03-07T10:00:00")
            )
        )
        val rideCalculated = Ride(segments).calculate()
        assertEquals(rideCalculated, BigDecimal.valueOf(29.0))
    }

    @Test
    fun shouldCalculateASundayNightRideWithSuccess() {
        val segments = mutableListOf(
            Segment(
                distance = BigDecimal.valueOf(10),
                date = LocalDateTime.parse("2021-03-07T23:00:00")
            )
        )
        val rideCalculated = Ride(segments).calculate()
        assertEquals(rideCalculated, BigDecimal.valueOf(50))
    }

    @Test
    fun shouldReturn1NegativeValueIfDistanceIsInvalid() {
        val result = assertThrows<Exception> {
            mutableListOf(
                Segment(
                    distance = BigDecimal.valueOf(-1),
                    date = LocalDateTime.parse("2021-03-07T23:00:00")
                )
            )
        }.message
        assertEquals(result, "Invalid distance")
    }

    @Test
    fun shouldCalculateADayRideWithMinPrice() {
        val segments = mutableListOf(
            Segment(
                distance = BigDecimal.valueOf(3),
                date = LocalDateTime.parse("2021-03-01T10:00:00")
            )
        )
        val rideCalculated = Ride(segments).calculate()
        assertEquals(rideCalculated, BigDecimal.valueOf(10))
    }

    @Test
    fun shouldCalculateARideWithAnySegments() {
        val segments = mutableListOf(
            Segment(
                distance = BigDecimal.valueOf(10),
                date = LocalDateTime.parse("2021-03-01T10:00:00")
            ),
            Segment(
                distance = BigDecimal.valueOf(10),
                date = LocalDateTime.parse("2021-03-01T10:00:00")
            )
        )
        val rideCalculated = Ride(segments).calculate()
        assertEquals(rideCalculated, BigDecimal.valueOf(42.0))
    }
}