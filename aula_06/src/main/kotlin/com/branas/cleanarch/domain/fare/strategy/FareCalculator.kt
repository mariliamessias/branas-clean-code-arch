package com.branas.cleanarch.domain.fare.strategy

import com.branas.cleanarch.domain.ride.Segment
import java.math.BigDecimal

interface FareCalculator {
    fun calculate(segment: Segment) : BigDecimal
}