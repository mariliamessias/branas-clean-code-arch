package com.branas.adapters.application.usecase

import com.branas.adapters.application.repository.DriverRepository
import com.branas.adapters.controller.response.GetDriverResponse
import org.springframework.stereotype.Component
import java.util.*

@Component
class GetDriver(
    private val driverRepository: DriverRepository
) {
    fun execute(id: String): GetDriverResponse {
        val driver = driverRepository.getReferenceById(UUID.fromString(id)).toDomain()
        return GetDriverResponse(
            id = driver.id!!,
            name = driver.name,
            email = driver.email.value,
            document = driver.document.value,
            carPlate = driver.carPlate.value,
            createdAt = driver.createdAt,
            updatedAt = driver.updatedAt
        )

    }
}