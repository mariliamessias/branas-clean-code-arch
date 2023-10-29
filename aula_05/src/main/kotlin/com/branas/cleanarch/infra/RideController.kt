package com.branas.cleanarch.infra

import com.branas.cleanarch.application.usecase.CalculateRide
import com.branas.cleanarch.application.usecase.RequestRide
import com.branas.cleanarch.infra.request.RequestRideRequest
import com.branas.cleanarch.infra.request.RideCalculatorRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class RideController(
    private var calculateRide: CalculateRide,
    private var requestRide: RequestRide
) {

    @CrossOrigin
    @PostMapping("/calculate_ride")
    fun calculate(@RequestBody request: RideCalculatorRequest): ResponseEntity<*> {
        return runCatching {
            val rideCalculated = calculateRide.execute(request)
            return ResponseEntity.ok().body(rideCalculated)

        }.getOrElse {
            ResponseEntity.unprocessableEntity().body(it.message)
        }
    }

    @CrossOrigin
    @PostMapping("/request_ride")
    fun request(@RequestBody request: RequestRideRequest): ResponseEntity<*> {
        return runCatching {
            val rideCalculated = requestRide.execute(request)
            return ResponseEntity.ok().body(rideCalculated)

        }.getOrElse {
            ResponseEntity.unprocessableEntity().body(it.message)
        }
    }
}