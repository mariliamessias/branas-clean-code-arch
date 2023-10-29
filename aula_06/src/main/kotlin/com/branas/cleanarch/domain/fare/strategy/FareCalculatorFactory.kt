package com.branas.cleanarch.domain.fare.strategy

import com.branas.cleanarch.domain.ride.Segment

class FareCalculatorFactory {

    companion object {
        fun create(segment: Segment): FareCalculator {
            if (segment.isOvernight() && !segment.isSunday()) {
                return OverNightFareCalculator()
            }
            if (segment.isOvernight() && segment.isSunday()) {
                return OverNightSundayFareCalculator()
            }
            if (!segment.isOvernight() && segment.isSunday()) {
                return SundayFareCalculator()
            }
            if (!segment.isOvernight() && !segment.isSunday()) {
                return NormalFareCalculator()
            }

            throw Exception("Invalid Segment")
        }
    }
}