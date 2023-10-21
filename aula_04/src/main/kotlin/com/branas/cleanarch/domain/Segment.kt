package com.branas.cleanarch.domain

import java.math.BigDecimal
import java.time.LocalDateTime

class Segment(val distance: BigDecimal, private val date: LocalDateTime) {
    fun isOvernight() = this.date.hour >= 22 || this.date.hour <= 6
    fun isSunday() = this.date.dayOfWeek.value == 7
}