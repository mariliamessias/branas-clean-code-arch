package com.branas.cleanarch.application.usecase

import com.branas.cleanarch.application.repository.RideRepository
import com.branas.cleanarch.infra.request.StartRideRequest
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class StartRide(
    private val rideRepository: RideRepository
) {

    fun execute(request: StartRideRequest) {
        val ride = rideRepository.getReferenceById(UUID.fromString(request.rideId)).toDomain()
        ride.start(LocalDateTime.parse(request.date))
        rideRepository.save(ride.toEntity())
    }
}