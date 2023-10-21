package com.branas.adapters.application.usecase

import com.branas.adapters.controller.request.RideCalculatorRequest
import com.branas.adapters.controller.response.RideCalculatorResponse
import com.branas.adapters.domain.Ride
import com.branas.adapters.domain.Segment
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