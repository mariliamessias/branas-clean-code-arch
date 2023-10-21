package com.branas.cleanarch.repository

import com.branas.cleanarch.repository.entities.Passengers
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PassengersRepository : JpaRepository<Passengers, UUID>