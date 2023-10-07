package com.branas.adapters.service

import com.branas.adapters.controller.request.CreateDriversRequest
import com.branas.adapters.controller.response.CreateDriversResponse
import com.branas.adapters.repository.DriversRepository
import com.branas.adapters.repository.entities.Drivers
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class DriversService(
    private val driversRepository: DriversRepository
) {
    fun create(request: CreateDriversRequest): CreateDriversResponse {
        val newDrivers = Drivers(
            name = request.name,
            email = request.email,
            carPlate = request.carPlate,
            document = request.document,
            createdAt = LocalDateTime.now()
        )
        val passengerCreated = driversRepository.save(newDrivers)
        return CreateDriversResponse(passengerCreated.id!!)
    }

    fun get(id: String): Drivers {
        return driversRepository.getReferenceById(UUID.fromString(id))
    }
}