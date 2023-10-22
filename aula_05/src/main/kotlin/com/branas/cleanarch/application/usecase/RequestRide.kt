package com.branas.cleanarch.application.usecase

import com.branas.cleanarch.application.repository.RideRepository
import com.branas.cleanarch.domain.ride.Ride
import com.branas.cleanarch.infra.request.RequestRideRequest
import com.branas.cleanarch.infra.response.RequestRideResponse
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class RequestRide(
    private val rideRepository: RideRepository
) {
    fun execute(requestRideRequest: RequestRideRequest): RequestRideResponse {
        val ride = Ride.create(
            passengerId = requestRideRequest.passengerId,
            to = requestRideRequest.to,
            from = requestRideRequest.from,
            date = LocalDateTime.parse(requestRideRequest.date)
        ).toEntity()

        val rideRequested = rideRepository.save(ride)
        return RequestRideResponse(rideRequested.id!!)
    }
}