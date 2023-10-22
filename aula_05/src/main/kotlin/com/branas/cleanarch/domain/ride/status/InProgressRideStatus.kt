package com.branas.cleanarch.domain.ride.status

import com.branas.cleanarch.domain.ride.Ride

class InProgressRideStatus(override val value: String = "in_progress", override val ride: Ride) : RideStatus() {

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
        this.ride.status = CompletedRideStatus(ride = this.ride)
    }
}