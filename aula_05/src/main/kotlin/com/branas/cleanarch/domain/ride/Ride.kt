package com.branas.cleanarch.domain.ride

import com.branas.cleanarch.application.repository.entities.RideEntity
import com.branas.cleanarch.domain.distance.Coord
import com.branas.cleanarch.domain.fare.chainOfResponsability.FareCalculatorHandler
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

private const val MIN_PRICE = 10.0

data class Ride(
    private val positions: MutableList<Position> = arrayListOf(),
    private var fareCalculator: FareCalculatorHandler? = null,
    val rideId: UUID? = null,
    val passengerId: UUID? = null,
    val from: Coord? = null,
    val to: Coord? = null,
    var status: String? = null,
    val requestDate: LocalDateTime? = null,
    var driverId: UUID? = null,
    var acceptDate: LocalDateTime? = null,
    var startDate: LocalDateTime? = null,
    var endDate: LocalDateTime? = null
) {
    fun addPosition(lat: BigDecimal, long: BigDecimal, date: LocalDateTime) {
        positions.add(Position(lat, long, date))
    }

    fun calculate(): BigDecimal {
        var price = BigDecimal.ZERO
        runCatching {
            positions.mapIndexed { index, position ->
                val nextPosition = positions[index + 1]
                val distance = com.branas.cleanarch.domain.distance.DistanceCalculator.calculate(
                    position.coord,
                    nextPosition.coord
                )
                val segment = Segment(distance, nextPosition.date)
//                val fareCalculator = FareCalculatorFactory.create(segment)
//                price = price.plus(fareCalculator.calculate(segment))
                price = price.plus(fareCalculator!!.handle(segment))
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

    fun toEntity() = RideEntity(
        id = this.rideId,
        passengerId = this.passengerId,
        driverId = this.driverId,
        fromLat = this.from!!.lat,
        fromLong = this.from.long,
        toLat = this.to!!.lat,
        toLong = this.to.long,
        status = this.status!!,
        createdAt = LocalDateTime.now(),
        requestDate = this.requestDate!!,
        acceptDate = this.acceptDate,
        startDate = this.startDate,
        endDate = this.endDate
    )

    fun accept(driverId: UUID, date: LocalDateTime) {
        this.driverId = driverId
        this.status = "accepted"
        this.acceptDate = date
    }

    fun start(date: LocalDateTime) {
        this.startDate = date
        this.status = "in_progress"
    }

    fun end(date: LocalDateTime) {
        this.endDate =  date
        this.status = "completed"
    }

    companion object {
        fun create(passengerId: UUID, from: Coord, to: Coord, date: LocalDateTime) = Ride(
            rideId = UUID.randomUUID(),
            passengerId = passengerId,
            from = from,
            to = to,
            status = "requested",
            requestDate = date
        )
    }
}