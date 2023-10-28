package com.branas.cleanarch.application.usecase

import com.branas.cleanarch.application.repository.DriverRepository
import com.branas.cleanarch.infra.request.CreateDriversRequest
import com.branas.cleanarch.infra.response.CreateDriverResponse
import com.branas.cleanarch.domain.driver.Driver
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