package com.branas.cleanarch.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class DistanceCalculatorTest {

    @Test
    fun shouldCalculateDistanceBetweenTwoCoordinates() {
        val from = Coord(BigDecimal.valueOf(-27.584905257808835), BigDecimal.valueOf(-48.545022195325124))
        val to = Coord(BigDecimal.valueOf(-27.496887588317275), BigDecimal.valueOf(-48.522234807851476))
        val distance = DistanceCalculator.calculate(from, to)
        Assertions.assertEquals(distance, BigDecimal.TEN)
    }

}