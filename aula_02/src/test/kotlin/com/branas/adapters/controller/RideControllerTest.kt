package com.branas.adapters.controller

import com.branas.adapters.IntegrationTests
import com.branas.cleanarch.controller.request.RideCalculatorRequest
import com.branas.cleanarch.controller.request.RideSegmentRequest
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.post

class RideControllerTest : IntegrationTests() {

    @Test
    fun executeCalculateRideWithSuccess() {
        val request = RideCalculatorRequest(
                segments = listOf(
                        RideSegmentRequest(
                                distance = 10,
                                date = "2021-03-01T10:00:00"
                        )
                )
        )
        mockMvc.post("/calculate_ride") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status { isOk() }
            content { string("{\"price\":21.0}") }
        }
    }

    @Test
    fun shouldThrownExceptionWhenDistanceIsInvalid() {
        val request = RideCalculatorRequest(
                segments = listOf(
                        RideSegmentRequest(
                                distance = -10,
                                date = "2021-03-01T10:00:00"
                        )
                )
        )
        mockMvc.post("/calculate_ride") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status { isUnprocessableEntity() }
            content { string("Invalid distance") }
        }
    }
}