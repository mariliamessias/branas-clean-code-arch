package com.branas.cleanarch.domain.strategies

import com.branas.cleanarch.domain.Segment
import java.math.BigDecimal

private const val FARE = 2.1

class NormalFareCalculator : FareCalculator {
    override fun calculate(segment: Segment): BigDecimal {
        return segment.distance.multiply(BigDecimal.valueOf(FARE))
    }
}