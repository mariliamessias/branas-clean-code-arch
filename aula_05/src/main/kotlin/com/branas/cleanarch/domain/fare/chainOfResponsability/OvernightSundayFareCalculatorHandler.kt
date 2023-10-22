package com.branas.cleanarch.domain.fare.chainOfResponsability

import com.branas.cleanarch.domain.ride.Segment
import org.springframework.stereotype.Component
import java.math.BigDecimal

private const val FARE = 5.0

@Component
class OvernightSundayFareCalculatorHandler(override val next: FareCalculatorHandler? = null) : FareCalculatorHandler {
    override fun handle(segment: Segment): BigDecimal {
        if (segment.isOvernight() && segment.isSunday()) {
            return segment.distance.multiply(BigDecimal.valueOf(FARE))
        }
        return this.next?.let { handle(segment) } ?: throw Exception("Invalid Segment")
    }
}