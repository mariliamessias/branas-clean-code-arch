package com.branas.cleanarch.application.usecase

import com.branas.cleanarch.application.repository.PassengerRepository
import com.branas.cleanarch.controller.request.CreatePassengersRequest
import com.branas.cleanarch.controller.response.CreatePassengerResponse
import com.branas.cleanarch.domain.Passenger
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CreatePassenger(
    private val passengerRepository: PassengerRepository
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