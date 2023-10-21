package com.branas.cleanarch.controller

import com.branas.cleanarch.controller.request.CreateDriversRequest
import com.branas.cleanarch.service.DriversService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class DriversController(
    private val driversService: DriversService
) {

    @PostMapping("/drivers")
    fun createDriver(@RequestBody request: CreateDriversRequest): ResponseEntity<*> {
        return runCatching {
            val result = driversService.create(request)
            return ResponseEntity(result, HttpStatus.CREATED)
        }.getOrElse {
            ResponseEntity.unprocessableEntity().body(it.message)
        }
    }

    @GetMapping("/drivers/{id}")
    fun getDriver(@PathVariable id: String): ResponseEntity<*> {
        return runCatching {
            val result = driversService.get(id)
            return ResponseEntity(result, HttpStatus.OK)
        }.getOrElse {
            ResponseEntity.internalServerError().body(it.message)
        }
    }
}