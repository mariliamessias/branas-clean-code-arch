package com.branas.cleanarch.domain.strategies

import com.branas.cleanarch.domain.Segment

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