package com.branas.cleanarch.application.usecase

import com.branas.cleanarch.UnitTests
import com.branas.cleanarch.application.repository.RideRepository
import com.branas.cleanarch.application.repository.entities.RideEntity
import com.branas.cleanarch.infra.request.AcceptRideRequest
import com.branas.cleanarch.infra.request.StartRideRequest
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

class StartRideTest : UnitTests() {

    @InjectMockKs
    lateinit var useCase: StartRide

    @MockK
    lateinit var repository: RideRepository

    @Test
    fun shouldStartRideWithSuccess() {
        val driverId = UUID.randomUUID().toString()
        val rideId = UUID.randomUUID().toString()

        val startRideRequest = StartRideRequest(
            rideId = rideId,
            date = "2021-03-01T10:00:00"
        )

        val entityResult = RideEntity(
            id = UUID.randomUUID(),
            passengerId = UUID.randomUUID(),
            createdAt = LocalDateTime.now(),
            requestDate = LocalDateTime.now(),
            status = "requested",
            fromLat = BigDecimal.ZERO,
            fromLong = BigDecimal.ZERO,
            toLat = BigDecimal.ZERO,
            toLong = BigDecimal.ZERO,
        )

        val entityUpdated = RideEntity(
            id = UUID.randomUUID(),
            passengerId = UUID.randomUUID(),
            createdAt = LocalDateTime.now(),
            requestDate = LocalDateTime.now(),
            status = "in_progress",
            fromLat = BigDecimal.ZERO,
            fromLong = BigDecimal.ZERO,
            toLat = BigDecimal.ZERO,
            toLong = BigDecimal.ZERO,
            driverId = UUID.fromString(driverId),
            acceptDate = LocalDateTime.now()
        )
        every { repository.getReferenceById(UUID.fromString(rideId)) } returns entityResult
        every { repository.save(any()) } returns entityUpdated

        Assertions.assertDoesNotThrow {
            useCase.execute(startRideRequest)
        }
    }
}