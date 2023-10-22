package com.branas.cleanarch.domain.ride.status

import com.branas.cleanarch.domain.ride.Ride

class CompletedRideStatus(override val value: String = "completed", override val ride: Ride) : RideStatus() {

    override fun request() {
        throw Exception("Invalid Status")
    }

    override fun accept() {
        throw Exception("Invalid Status")
    }

    override fun start() {
        throw Exception("Invalid Status")
    }

    override fun end() {
        throw Exception("Invalid Status")
    }
}