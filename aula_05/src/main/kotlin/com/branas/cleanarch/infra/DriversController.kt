package com.branas.cleanarch.infra

import com.branas.cleanarch.application.usecase.CreateDriver
import com.branas.cleanarch.application.usecase.GetDriver
import com.branas.cleanarch.infra.request.CreateDriversRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class DriversController(
    private val getDriver: GetDriver,
    private val createDriver: CreateDriver
) {

    @PostMapping("/drivers")
    fun createDriver(@RequestBody request: CreateDriversRequest): ResponseEntity<*> {
        return runCatching {
            val result = createDriver.execute(request)
            return ResponseEntity(result, HttpStatus.CREATED)
        }.getOrElse {
            ResponseEntity.unprocessableEntity().body(it.message)
        }
    }

    @GetMapping("/drivers/{id}")
    fun getDriver(@PathVariable id: String): ResponseEntity<*> {
        return runCatching {
            val result = getDriver.execute(id)
            return ResponseEntity(result, HttpStatus.OK)
        }.getOrElse {
            ResponseEntity.internalServerError().body(it.message)
        }
    }
}