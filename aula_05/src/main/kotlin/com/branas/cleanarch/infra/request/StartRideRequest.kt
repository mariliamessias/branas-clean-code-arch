package com.branas.cleanarch.infra.request

data class StartRideRequest(
    val rideId: String,
    val date: String
)