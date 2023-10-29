package com.branas.cleanarch.domain.ride.status

import com.branas.cleanarch.domain.ride.Ride

class RequestedRideStatus(override val value: String = "requested", override val ride: Ride) : RideStatus() {

    override fun request() {
        throw Exception("Invalid Status")
    }

    override fun accept() {
        this.ride.status = AcceptedRideStatus(ride = this.ride)
    }

    override fun start() {
        throw Exception("Invalid Status")
    }

    override fun end() {
        throw Exception("Invalid Status")
    }
}