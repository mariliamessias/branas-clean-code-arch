package com.branas.refactoring.repository

import com.branas.refactoring.repository.entities.Passenger
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PassengersRepository  : JpaRepository<Passenger, UUID>{
}