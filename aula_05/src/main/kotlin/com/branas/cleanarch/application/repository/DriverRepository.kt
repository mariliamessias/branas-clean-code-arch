package com.branas.cleanarch.application.repository

import com.branas.cleanarch.application.repository.entities.DriverEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DriverRepository : JpaRepository<DriverEntity, UUID>