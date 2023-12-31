package com.branas.adapters.repository

import com.branas.adapters.repository.entities.Passengers
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PassengersRepository : JpaRepository<Passengers, UUID>