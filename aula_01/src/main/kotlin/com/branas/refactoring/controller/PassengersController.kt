package com.branas.refactoring.controller

import com.branas.refactoring.controller.request.CreatePassengersRequest
import com.branas.refactoring.services.PassengersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PassengersController {
    @Autowired
    private lateinit var passengersService: PassengersService

    @PostMapping("/passengers")
    fun createPassenger(@RequestBody request: CreatePassengersRequest): ResponseEntity<*> {
        return runCatching {
            val result = passengersService.create(request)
            return ResponseEntity(result, HttpStatus.CREATED)
        }.getOrElse {
            ResponseEntity.unprocessableEntity().body(it.message)
        }
    }
}