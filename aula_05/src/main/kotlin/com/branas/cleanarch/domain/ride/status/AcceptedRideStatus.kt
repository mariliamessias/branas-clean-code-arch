package com.branas.cleanarch.domain.ride.status

import com.branas.cleanarch.domain.ride.Ride

class AcceptedRideStatus(override val value: String = "accepted", override val ride: Ride) : RideStatus() {

    override fun request() {
        throw Exception("Invalid Status")
    }

    override fun accept() {
        throw Exception("Invalid Status")
    }

    override fun start() {
        this.ride.status = InProgressRideStatus(ride = this.ride)
    }

    override fun end() {
        throw Exception("Invalid Status")
    }
}