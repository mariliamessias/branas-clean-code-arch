package com.branas.cleanarch.application.repository

import com.branas.cleanarch.application.repository.entities.RideEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RideRepository : JpaRepository<RideEntity, UUID>