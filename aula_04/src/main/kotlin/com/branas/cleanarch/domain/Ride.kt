package com.branas.cleanarch.domain

import com.branas.cleanarch.domain.strategies.FareCalculatorFactory
import java.math.BigDecimal
import java.time.LocalDateTime

private const val MIN_PRICE = 10.0

data class Ride(
    val positions: MutableList<Position> = arrayListOf()
) {

    fun addPosition(lat: BigDecimal, long: BigDecimal, date: LocalDateTime) {
        positions.add(Position(lat, long, date))
    }

    fun calculate(): BigDecimal {
        var price = BigDecimal.ZERO
        runCatching {
            positions.mapIndexed { index, position ->
                val nextPosition = positions[index + 1]
                val distance = DistanceCalculator.calculate(position.coord, nextPosition.coord)
                val segment = Segment(distance, nextPosition.date)
                val fareCalculator = FareCalculatorFactory.create(segment)
                price = price.plus(fareCalculator.calculate(segment))
            }
        }.onFailure {
            when (it) {
                is IndexOutOfBoundsException -> {}
                else -> throw it
            }
        }

        return takeIf { price < BigDecimal.valueOf(MIN_PRICE) }
            ?.let { BigDecimal.valueOf(MIN_PRICE) }
            ?: run { price }
    }
}