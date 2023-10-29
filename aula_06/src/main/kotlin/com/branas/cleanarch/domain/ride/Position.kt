package com.branas.cleanarch.domain.ride

import com.branas.cleanarch.domain.distance.Coord
import java.math.BigDecimal
import java.time.LocalDateTime

data class Position(val coord: com.branas.cleanarch.domain.distance.Coord, val date: LocalDateTime) {

    constructor(lat: BigDecimal, long: BigDecimal, date: LocalDateTime) : this(
        com.branas.cleanarch.domain.distance.Coord(
            lat,
            long
        ), date)
}