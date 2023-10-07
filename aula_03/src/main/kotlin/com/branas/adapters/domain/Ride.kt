package com.branas.adapters.domain

import java.math.BigDecimal
private const val OVERNIGHT_FARE = 3.90
private const val OVERNIGHT_SUNDAY_FARE = 5
private const val SUNDAY_FARE = 2.9
private const val NORMAL_FARE = 2.1
private const val MIN_PRICE = 10

data class Ride(val segments: MutableList<Segment> = arrayListOf()) {
    fun calculate(): BigDecimal {
        var price = BigDecimal.ZERO
        segments.map {
            if (it.isOvernight() && !it.isSunday()) {
                price = price.plus(it.distance.multiply(OVERNIGHT_FARE.toBigDecimal()))
            }
            if (it.isOvernight() && it.isSunday()) {
                price = price.plus(it.distance.multiply(OVERNIGHT_SUNDAY_FARE.toBigDecimal()))
            }
            if (!it.isOvernight() && it.isSunday()) {
                price = price.plus(it.distance * SUNDAY_FARE.toBigDecimal())
            }
            if (!it.isOvernight() && !it.isSunday()) {
                price = price.plus(it.distance * NORMAL_FARE.toBigDecimal())
            }
        }
        return takeIf { price < MIN_PRICE.toBigDecimal() }
                ?.let { MIN_PRICE.toBigDecimal() }
                ?: run { price }
    }
}