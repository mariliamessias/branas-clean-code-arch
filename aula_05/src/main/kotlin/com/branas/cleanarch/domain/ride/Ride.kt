package com.branas.cleanarch.domain.ride

import com.branas.cleanarch.application.repository.entities.RideEntity
import com.branas.cleanarch.domain.distance.Coord
import com.branas.cleanarch.domain.fare.chainOfResponsability.FareCalculatorHandler
import com.branas.cleanarch.domain.fare.strategy.FareCalculatorFactory
import com.branas.cleanarch.domain.ride.status.RideStatus
import com.branas.cleanarch.domain.ride.status.RideStatusFactory
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

private const val MIN_PRICE = 10.0

data class Ride(
    private val positions: MutableList<Position> = arrayListOf(),
    private var fareCalculator: FareCalculatorHandler? = null,
    var rideId: UUID? = null,
    var passengerId: UUID? = null,
    var from: Coord? = null,
    var to: Coord? = null,
    var status: RideStatus? = null,
    var requestDate: LocalDateTime? = null,
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
                val fareCalculator = FareCalculatorFactory.create(segment)
                price = price.plus(fareCalculator.calculate(segment))
                //price = price.plus(fareCalculator!!.handle(segment))
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
        fromLong = this.from!!.long,
        toLat = this.to!!.lat,
        toLong = this.to!!.long,
        status = this.status!!.value!!,
        createdAt = LocalDateTime.now(),
        requestDate = this.requestDate!!,
        acceptDate = this.acceptDate,
        startDate = this.startDate,
        endDate = this.endDate
    )

    fun accept(driverId: UUID, date: LocalDateTime) {
        this.driverId = driverId
        this.status!!.accept()
        this.acceptDate = date
    }

    fun start(date: LocalDateTime) {
        this.startDate = date
        this.status!!.start()
    }

    fun end(date: LocalDateTime) {
        this.endDate = date
        this.status!!.end()
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

    constructor(
        rideId: UUID?,
        passengerId: UUID,
        from: Coord,
        to: Coord,
        status: String,
        requestDate: LocalDateTime
    ) : this() {
        this.status = RideStatusFactory.create(this, status)
        this.rideId = rideId
        this.passengerId = passengerId
        this.from = from
        this.to = to
        this.requestDate = requestDate
    }

    constructor(
        rideId: UUID?,
        passengerId: UUID?,
        driverId: UUID?,
        status: String,
        from: Coord,
        to: Coord,
        requestDate: LocalDateTime,
        acceptDate: LocalDateTime?,
        startDate: LocalDateTime?,
        endDate: LocalDateTime?
    ) : this() {
        this.status = RideStatusFactory.create(this, status)
        this.driverId = driverId
        this.rideId = rideId
        this.passengerId = passengerId
        this.from = from
        this.to = to
        this.requestDate = requestDate
        this.acceptDate = acceptDate
        this.startDate = startDate
        this.endDate = endDate
    }

}