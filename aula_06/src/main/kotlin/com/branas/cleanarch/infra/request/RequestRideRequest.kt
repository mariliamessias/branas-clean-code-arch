package com.branas.cleanarch.infra.request

import com.branas.cleanarch.domain.distance.Coord
import java.util.*

data class RequestRideRequest(
    val passengerId: UUID,
    val from: Coord,
    val to: Coord,
    val date: String
)