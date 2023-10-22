package com.branas.cleanarch.application.usecase

import com.branas.cleanarch.application.repository.RideRepository
import com.branas.cleanarch.infra.request.EndRideRequest
import com.branas.cleanarch.infra.request.StartRideRequest
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class EndRide(
    private val rideRepository: RideRepository
) {

    fun execute(request: EndRideRequest) {
        val ride = rideRepository.getReferenceById(UUID.fromString(request.rideId)).toDomain()
        ride.end(LocalDateTime.parse(request.date))
        rideRepository.save(ride.toEntity())
    }
}