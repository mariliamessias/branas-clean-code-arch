package com.branas.cleanarch.controller

import com.branas.cleanarch.IntegrationTests
import com.branas.cleanarch.controller.request.CreateDriversRequest
import org.hamcrest.CoreMatchers.containsString
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

class DriversControllerTest : IntegrationTests() {

    @Test
    fun executeCreateDriversWithSuccess() {
        val request = CreateDriversRequest(
            name = "testName",
            email = "test@email.com",
            document = "83432616074",
            carPlate = "666"
        )
        mockMvc.post("/drivers") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status { isCreated() }
            content { string(containsString("driverId")) }
        }
    }

    @Test
    fun executeCreateDriversFailureWhenEmailIsInvalid() {
        val request = CreateDriversRequest(
            name = "testName",
            email = "test",
            document = "83432616074",
            carPlate = "666"
        )
        mockMvc.post("/drivers") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status { isUnprocessableEntity() }
            content { string(containsString("Invalid email")) }
        }
    }

    @Test
    fun executeGetDriversWithSuccess() {
        val request = CreateDriversRequest(
            name = "testName",
            email = "test@email.com",
            document = "83432616074",
            carPlate = "666"
        )
        val result = mockMvc.post("/drivers") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andReturn().response.contentAsString.substringAfter("{\"driverId\":\"").substringBefore("\"}")

        mockMvc.get("/drivers/${result}") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status { isOk() }
            content { string(containsString("name\":\"testName\",\"email\":\"test@email.com\",\"document\":\"83432616074\"")) }
        }
    }
}