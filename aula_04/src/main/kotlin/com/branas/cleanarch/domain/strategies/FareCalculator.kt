package com.branas.cleanarch.domain.strategies

import com.branas.cleanarch.domain.Segment
import java.math.BigDecimal

interface FareCalculator {
    fun calculate(segment: Segment) : BigDecimal
}