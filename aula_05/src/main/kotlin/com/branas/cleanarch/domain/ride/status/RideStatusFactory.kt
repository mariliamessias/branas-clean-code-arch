package com.branas.cleanarch.domain.ride.status

import com.branas.cleanarch.domain.ride.Ride

class RideStatusFactory {

    companion object {
        fun create(ride: Ride, status: String): RideStatus {
            if (status == "requested") {
                return RequestedRideStatus(ride = ride)
            }
            if (status == "accepted") {
                return AcceptedRideStatus(ride = ride)
            }
            if (status == "in_progress") {
                return InProgressRideStatus(ride = ride)
            }
            if (status == "completed") {
                return CompletedRideStatus(ride = ride)
            }
            throw Exception("invalid status")
        }
    }
}