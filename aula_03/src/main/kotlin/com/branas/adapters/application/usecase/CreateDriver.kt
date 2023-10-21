package com.branas.adapters.application.usecase

import com.branas.adapters.application.repository.DriverRepository
import com.branas.adapters.controller.request.CreateDriversRequest
import com.branas.adapters.controller.response.CreateDriverResponse
import com.branas.adapters.domain.Driver
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CreateDriver(
    private val driverRepository: DriverRepository
) {
    fun execute(request: CreateDriversRequest): CreateDriverResponse {
        val newDriver = Driver.create(
            name = request.name,
            email = request.email,
            carPlate = request.carPlate,
            document = request.document,
            createdAt = LocalDateTime.now()
        ).toEntity()
        val passengerCreated = driverRepository.save(newDriver)
        return CreateDriverResponse(passengerCreated.id!!)
    }
}