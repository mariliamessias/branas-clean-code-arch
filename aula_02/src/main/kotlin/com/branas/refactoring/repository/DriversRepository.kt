package com.branas.refactoring.repository

import com.branas.refactoring.repository.entities.Drivers
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DriversRepository : JpaRepository<Drivers, UUID>