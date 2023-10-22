package com.branas.cleanarch.domain.fare.strategy

import com.branas.cleanarch.domain.ride.Segment
import java.math.BigDecimal
private const val FARE =  2.9

class SundayFareCalculator : FareCalculator {
    override fun calculate(segment: Segment): BigDecimal {
        return segment.distance.multiply(BigDecimal.valueOf(FARE))
    }
}