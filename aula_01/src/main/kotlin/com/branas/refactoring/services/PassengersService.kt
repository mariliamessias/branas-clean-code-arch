package com.branas.refactoring.services

import com.branas.refactoring.controller.request.CreatePassengersRequest
import com.branas.refactoring.controller.response.CreatePassengersResponse
import com.branas.refactoring.repository.PassengersRepository
import com.branas.refactoring.repository.entities.Passenger
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PassengersService(
        private var passengersRepository: PassengersRepository
) {

    fun create(request: CreatePassengersRequest): CreatePassengersResponse {
        val newPassenger = Passenger(
                name = request.name,
                email = request.email,
                document = request.document,
                createdAt = LocalDateTime.now()
        )
        val passengerCreated = passengersRepository.save(newPassenger)
        return CreatePassengersResponse(passengerCreated.id!!)
    }
}