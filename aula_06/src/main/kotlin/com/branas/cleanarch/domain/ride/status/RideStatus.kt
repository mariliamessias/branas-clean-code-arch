package com.branas.cleanarch.domain.ride.status

import com.branas.cleanarch.domain.ride.Ride

abstract class RideStatus {
    abstract val value: String?
    abstract val ride: Ride

    abstract fun request()
    abstract fun accept()
    abstract fun start()
    abstract fun end()
}