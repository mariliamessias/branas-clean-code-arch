package com.branas.cleanarch.application.usecase

import com.branas.cleanarch.application.repository.RideRepository
import com.branas.cleanarch.infra.request.AcceptRideRequest
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class AcceptRide(
    private val rideRepository: RideRepository
) {

    fun execute(request: AcceptRideRequest) {
        val ride = rideRepository.getReferenceById(UUID.fromString(request.rideId)).toDomain()
        ride.accept(UUID.fromString(request.driverId), LocalDateTime.parse(request.date))
        rideRepository.save(ride.toEntity())
    }
}