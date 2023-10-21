package com.branas.cleanarch.controller

import com.branas.cleanarch.controller.request.RideCalculatorRequest
import com.branas.cleanarch.controller.response.RideCalculatorResponse
import com.branas.cleanarch.model.Ride
import com.branas.cleanarch.model.Segment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@RestController
class RideController {

    @PostMapping("/calculate_ride")
    fun calculate(@RequestBody request: RideCalculatorRequest): ResponseEntity<*> {
        return runCatching {
            request.segments
                    .map { Segment(BigDecimal.valueOf(it.distance.toLong()), LocalDateTime.parse(it.date)) }
                    .toMutableList()
                    .let {
                        val price = Ride(it).calculate()
                        return ResponseEntity.ok().body(RideCalculatorResponse(price))
                    }
        }.getOrElse {
            ResponseEntity.unprocessableEntity().body(it.message)
        }
    }
}