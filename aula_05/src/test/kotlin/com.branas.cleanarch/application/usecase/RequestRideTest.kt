package com.branas.cleanarch.application.usecase

import com.branas.cleanarch.UnitTests
import com.branas.cleanarch.application.repository.RideRepository
import com.branas.cleanarch.application.repository.entities.RideEntity
import com.branas.cleanarch.domain.distance.Coord
import com.branas.cleanarch.infra.request.RequestRideRequest
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

class RequestRideTest : UnitTests() {

    @InjectMockKs
    lateinit var useCase: RequestRide

    @MockK
    lateinit var repository: RideRepository

    @Test
    fun shouldRequestRideWithSuccess() {
        val passengerId = UUID.randomUUID()

        val requestRide = RequestRideRequest(
            passengerId = passengerId,
            from = Coord(BigDecimal.valueOf(-27.584905257808835), BigDecimal.valueOf(-48.545022195325124)),
            to = Coord(BigDecimal.valueOf(-27.496887588317275), BigDecimal.valueOf(-48.522234807851476)),
            date = "2021-03-01T10:00:00"
        )

        val entityResult = RideEntity(
            id = UUID.randomUUID(),
            passengerId = passengerId,
            createdAt = LocalDateTime.now(),
            requestDate = LocalDateTime.now(),
            status = "requested",
            fromLat = requestRide.from.lat,
            fromLong = requestRide.from.long,
            toLat = requestRide.to.lat,
            toLong = requestRide.to.long,
        )

        every { repository.save(any()) } returns entityResult
        val result = useCase.execute(requestRide)
        Assertions.assertNotNull(result)
    }
}