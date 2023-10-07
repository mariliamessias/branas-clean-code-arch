package com.branas.adapters.application.usecase

import com.branas.adapters.application.repository.PassengerRepository
import com.branas.adapters.controller.response.GetPassengerResponse
import org.springframework.stereotype.Component
import java.util.*

@Component
class GetPassenger(
    private val passengerRepository: PassengerRepository
) {
    fun execute(id: String): GetPassengerResponse {
        val passenger = passengerRepository.getReferenceById(UUID.fromString(id)).toDomain()
        return GetPassengerResponse(
            id = passenger.id!!,
            name = passenger.name,
            document = passenger.document.value,
            email = passenger.email.value,
            createdAt = passenger.createdAt,
            updatedAt = passenger.updatedAt
        )
    }
}