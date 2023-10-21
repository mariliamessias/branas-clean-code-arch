package com.branas.cleanarch.domain.strategies

import com.branas.cleanarch.domain.Segment
import java.math.BigDecimal

private const val FARE = 3.9

class OverNightFareCalculator : FareCalculator {
    override fun calculate(segment: Segment): BigDecimal {
        return segment.distance.multiply(FARE.toBigDecimal())
    }
}