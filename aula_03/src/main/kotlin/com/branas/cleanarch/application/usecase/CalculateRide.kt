package com.branas.cleanarch.application.usecase

import com.branas.cleanarch.controller.request.RideCalculatorRequest
import com.branas.cleanarch.controller.response.RideCalculatorResponse
import com.branas.cleanarch.domain.Ride
import com.branas.cleanarch.domain.Segment
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.LocalDateTime

@Component
class CalculateRide {

    fun execute(request: RideCalculatorRequest): RideCalculatorResponse {
        return request.segments
            .map { Segment(BigDecimal.valueOf(it.distance.toLong()), LocalDateTime.parse(it.date)) }
            .toMutableList()
            .let {
                val price = Ride(it).calculate()
                RideCalculatorResponse(price)
            }
    }
}