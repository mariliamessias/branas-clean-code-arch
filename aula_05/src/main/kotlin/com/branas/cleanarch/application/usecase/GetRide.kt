package com.branas.cleanarch.application.usecase

import com.branas.cleanarch.application.repository.RideRepository
import com.branas.cleanarch.infra.response.GetRideResponse
import org.springframework.stereotype.Component
import java.util.*

@Component
class GetRide(
    private val rideRepository: RideRepository
) {
    fun execute(id: String): GetRideResponse {
        val ride = rideRepository.getReferenceById(UUID.fromString(id)).toDomain()
        return GetRideResponse(
            rideId = ride.rideId!!,
            status = ride.status!!,
            requestDate = ride.requestDate!!
        )
    }
}