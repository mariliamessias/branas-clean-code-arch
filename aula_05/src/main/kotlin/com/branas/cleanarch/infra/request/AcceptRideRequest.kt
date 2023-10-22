package com.branas.cleanarch.infra.request

data class AcceptRideRequest(
    val rideId: String,
    val driverId: String,
    val date: String
)