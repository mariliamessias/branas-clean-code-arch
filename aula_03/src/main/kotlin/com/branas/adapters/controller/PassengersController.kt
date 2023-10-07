package com.branas.adapters.controller

import com.branas.adapters.application.usecase.CreatePassenger
import com.branas.adapters.application.usecase.GetPassenger
import com.branas.adapters.controller.request.CreatePassengersRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class PassengersController(
    private val getPassenger: GetPassenger,
    private val createPassenger: CreatePassenger
) {

    @PostMapping("/passengers")
    fun createPassenger(@RequestBody request: CreatePassengersRequest): ResponseEntity<*> {
        return runCatching {
            val result = createPassenger.execute(request)
            return ResponseEntity(result, HttpStatus.CREATED)
        }.getOrElse {
            ResponseEntity.unprocessableEntity().body(it.message)
        }
    }

    @GetMapping("/passengers/{id}")
    fun getPassenger(@PathVariable id: String): ResponseEntity<*> {
        return runCatching {
            val result = getPassenger.execute(id)
            return ResponseEntity(result, HttpStatus.OK)
        }.getOrElse {
            ResponseEntity.internalServerError().body(it.message)
        }
    }
}