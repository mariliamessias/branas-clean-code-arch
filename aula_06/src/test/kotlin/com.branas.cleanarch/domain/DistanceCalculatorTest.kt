package com.branas.cleanarch.domain

import com.branas.cleanarch.domain.distance.Coord
import com.branas.cleanarch.domain.distance.DistanceCalculator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class DistanceCalculatorTest {

    @Test
    fun shouldCalculateDistanceBetweenTwoCoordinates() {
        val from = com.branas.cleanarch.domain.distance.Coord(
            BigDecimal.valueOf(-27.584905257808835),
            BigDecimal.valueOf(-48.545022195325124)
        )
        val to = com.branas.cleanarch.domain.distance.Coord(
            BigDecimal.valueOf(-27.496887588317275),
            BigDecimal.valueOf(-48.522234807851476)
        )
        val distance = com.branas.cleanarch.domain.distance.DistanceCalculator.calculate(from, to)
        Assertions.assertEquals(distance, BigDecimal.TEN)
    }

}