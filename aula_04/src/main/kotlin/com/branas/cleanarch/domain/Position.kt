package com.branas.cleanarch.domain

import java.math.BigDecimal
import java.time.LocalDateTime

data class Position(val coord: Coord, val date: LocalDateTime) {

    constructor(lat: BigDecimal, long: BigDecimal, date: LocalDateTime) : this(Coord(lat, long), date)
}