package com.branas.adapters.service

import com.branas.adapters.controller.request.CreatePassengersRequest
import com.branas.adapters.controller.response.CreatePassengersResponse
import com.branas.adapters.repository.PassengersRepository
import com.branas.adapters.repository.entities.Passengers
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class PassengersService(
    private val passengersRepository: PassengersRepository
) {
    fun create(request: CreatePassengersRequest): CreatePassengersResponse {
        val newPassengers = Passengers(
            name = request.name,
            email = request.email,
            document = request.document,
            createdAt = LocalDateTime.now()
        )
        val passengerCreated = passengersRepository.save(newPassengers)
        return CreatePassengersResponse(passengerCreated.id!!)
    }

    fun get(id: String): Passengers {
        return passengersRepository.getReferenceById(UUID.fromString(id))
    }
}