package com.branas.cleanarch.domain.distance

import java.math.BigDecimal
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class DistanceCalculator {
    companion object {
        fun calculate(from: com.branas.cleanarch.domain.distance.Coord, to: com.branas.cleanarch.domain.distance.Coord): BigDecimal {
            val earthRadius = 6371
            val degreesToRadians = Math.PI / 180
            val deltaLat = (to.lat.toDouble() - from.lat.toDouble()) * degreesToRadians
            val deltaLon = (to.long.toDouble() - to.long.toDouble()) * degreesToRadians
            val a =
                sin(deltaLat / 2) * sin(deltaLat / 2) +
                        cos(from.lat.toDouble() * degreesToRadians) *
                        cos(to.lat.toDouble() * degreesToRadians) *
                        sin(deltaLon / 2) *
                        sin(deltaLon / 2);
            val c = 2 * atan2(sqrt(a), sqrt(1 - a))
            return (earthRadius * c).toBigDecimal().setScale(0, BigDecimal.ROUND_HALF_UP)
        }
    }
}