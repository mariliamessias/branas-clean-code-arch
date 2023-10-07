package com.branas.adapters.model

import java.math.BigDecimal
import java.time.LocalDateTime

class Segment(val distance: BigDecimal, val date: LocalDateTime) {

    init {
        if (!isValidDistance()) throw Exception("Invalid distance")
    }

    fun isOvernight() = this.date.hour >= 22 || this.date.hour <= 6
    fun isSunday() = this.date.dayOfWeek.value == 7
    private fun isValidDistance() = this.distance > BigDecimal.ZERO
}