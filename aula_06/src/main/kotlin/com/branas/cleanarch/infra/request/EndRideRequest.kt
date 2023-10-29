package com.branas.cleanarch.infra.request

data class EndRideRequest(
    val rideId: String,
    val date: String
)