package com.branas.cleanarch.domain.fare.chainOfResponsability

import com.branas.cleanarch.domain.ride.Segment
import java.math.BigDecimal

interface FareCalculatorHandler {
    val next: FareCalculatorHandler?
    fun handle(segment: Segment): BigDecimal
}