package com.branas.cleanarch.infra

import com.branas.cleanarch.application.usecase.CalculateRide
import com.branas.cleanarch.infra.request.RideCalculatorRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class RideController(
    private var calculateRide: CalculateRide
) {

    @PostMapping("/calculate_ride")
    fun calculate(@RequestBody request: RideCalculatorRequest): ResponseEntity<*> {
        return runCatching {
            val rideCalculated = calculateRide.execute(request)
            return ResponseEntity.ok().body(rideCalculated)

        }.getOrElse {
            ResponseEntity.unprocessableEntity().body(it.message)
        }
    }
}