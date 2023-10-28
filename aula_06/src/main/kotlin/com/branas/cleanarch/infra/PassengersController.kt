package com.branas.cleanarch.infra

import com.branas.cleanarch.application.usecase.CreatePassenger
import com.branas.cleanarch.application.usecase.GetPassenger
import com.branas.cleanarch.infra.request.CreatePassengersRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class PassengersController(
    private val getPassenger: GetPassenger,
    private val createPassenger: CreatePassenger
) {

    @CrossOrigin
    @PostMapping("/passengers")
    fun createPassenger(@RequestBody request: CreatePassengersRequest): ResponseEntity<*> {
        return runCatching {
            val result = createPassenger.execute(request)
            return ResponseEntity(result, HttpStatus.CREATED)
        }.getOrElse {
            ResponseEntity.unprocessableEntity().body(it.message)
        }
    }

    @CrossOrigin
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