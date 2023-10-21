package com.branas.cleanarch.application.usecase

import com.branas.cleanarch.domain.Ride
import com.branas.cleanarch.infra.request.RideCalculatorRequest
import com.branas.cleanarch.infra.response.RideCalculatorResponse
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CalculateRide {

    fun execute(request: RideCalculatorRequest): RideCalculatorResponse {
        val ride = Ride()
        return request.positions
            .map { ride.addPosition(it.lat, it.long, LocalDateTime.parse(it.date)) }
            .toMutableList()
            .let {
                val price = ride.calculate()
                RideCalculatorResponse(price)
            }
    }
}