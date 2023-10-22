package com.branas.cleanarch.infra.response

import java.time.LocalDateTime
import java.util.UUID

data class GetRideResponse(
    private val rideId: UUID,
    private val status: String,
    private val requestDate: LocalDateTime
)