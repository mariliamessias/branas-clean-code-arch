package com.branas.cleanarch.application.repository.entities

import com.branas.cleanarch.configuration.NoArgs
import com.branas.cleanarch.domain.distance.Coord
import com.branas.cleanarch.domain.ride.Ride
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Entity
@Table
@NoArgs
class RideEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    val id: UUID? = null,

    @Column(nullable = false)
    val passengerId: UUID? = null,

    @Column
    val driverId: UUID? = null,

    @Column(nullable = false)
    val fromLat: BigDecimal,

    @Column(nullable = false)
    val fromLong: BigDecimal,

    @Column(nullable = false)
    val toLat: BigDecimal,

    @Column(nullable = false)
    val toLong: BigDecimal,

    @Column(nullable = false)
    val status: String,

    @Column
    val requestDate: LocalDateTime,

    @Column
    val acceptDate: LocalDateTime? = null,

    @Column
    val startDate: LocalDateTime? = null,

    @Column
    val endDate: LocalDateTime? = null,

    @Column
    val price: BigDecimal? = null,

    @Column
    val createdAt: LocalDateTime,

    @Column
    val updatedAt: LocalDateTime? = null
) {
    fun toDomain() = Ride(
        rideId = id,
        passengerId = passengerId,
        driverId = driverId,
        status = status,
        from = Coord(lat = fromLat, long = fromLong),
        to = Coord(lat = toLat, long = toLong),
        requestDate = requestDate,
        acceptDate = acceptDate,
        startDate = startDate,
        endDate = endDate
    )
}