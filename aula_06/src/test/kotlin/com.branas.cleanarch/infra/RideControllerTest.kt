package com.branas.cleanarch.infra

import com.branas.cleanarch.IntegrationTests
import com.branas.cleanarch.infra.request.RideCalculatorRequest
import com.branas.cleanarch.infra.request.RidePositionRequest
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.post
import java.math.BigDecimal

class RideControllerTest : IntegrationTests() {

    @Test
    fun executeCalculateRideWithSuccess() {
        val request = RideCalculatorRequest(
            positions = listOf(
                RidePositionRequest(
                    BigDecimal.valueOf(-27.584905257808835),
                    BigDecimal.valueOf(-48.545022195325124),
                    date = "2021-03-01T10:00:00"
                ),
                RidePositionRequest(
                    BigDecimal.valueOf(-27.496887588317275),
                    BigDecimal.valueOf(-48.522234807851476),
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
}