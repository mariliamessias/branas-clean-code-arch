package com.branas.cleanarch.application.usecase

import com.branas.cleanarch.application.repository.PassengerRepository
import com.branas.cleanarch.infra.response.GetPassengerResponse
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