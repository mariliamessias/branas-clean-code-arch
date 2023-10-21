package com.branas.adapters.application.usecase

import com.branas.adapters.controller.request.CreatePassengersRequest
import com.branas.adapters.controller.response.CreatePassengerResponse
import com.branas.adapters.domain.Passenger
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CreatePassenger(
    private val passengerRepository: com.branas.adapters.application.repository.PassengerRepository
) {
    fun execute(request: CreatePassengersRequest): CreatePassengerResponse {
        val newPassenger = Passenger.create(
            name = request.name,
            email = request.email,
            document = request.document,
            createdAt = LocalDateTime.now()
        ).toEntity()
        val passengerCreated = passengerRepository.save(newPassenger)
        return CreatePassengerResponse(passengerCreated.id!!)
    }
}