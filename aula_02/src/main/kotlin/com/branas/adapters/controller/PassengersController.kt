package com.branas.adapters.controller

import com.branas.adapters.controller.request.CreatePassengersRequest
import com.branas.adapters.service.PassengersService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class PassengersController(
    private val passengersService: PassengersService
) {

    @PostMapping("/passengers")
    fun createPassenger(@RequestBody request: CreatePassengersRequest): ResponseEntity<*> {
        return runCatching {
            val result = passengersService.create(request)
            return ResponseEntity(result, HttpStatus.CREATED)
        }.getOrElse {
            ResponseEntity.unprocessableEntity().body(it.message)
        }
    }

    @GetMapping("/passengers/{id}")
    fun getPassenger(@PathVariable id: String): ResponseEntity<*> {
        return runCatching {
            val result = passengersService.get(id)
            return ResponseEntity(result, HttpStatus.OK)
        }.getOrElse {
            ResponseEntity.internalServerError().body(it.message)
        }
    }
}