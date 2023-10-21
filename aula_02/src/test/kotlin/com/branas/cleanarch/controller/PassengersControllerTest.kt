package com.branas.cleanarch.controller

import com.branas.cleanarch.IntegrationTests
import com.branas.cleanarch.controller.request.CreatePassengersRequest
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

class PassengersControllerTest : IntegrationTests() {

    @Test
    fun executeCreatePassengersWithSuccess() {
        val request = CreatePassengersRequest(
            name = "testName",
            email = "test@email.com",
            document = "83432616074"
        )
        mockMvc.post("/passengers") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status { isCreated() }
            content { string(CoreMatchers.containsString("passengerId")) }
        }
    }

    @Test
    fun executeCreatePassengersFailureWhenEmailIsInvalid() {
        val request = CreatePassengersRequest(
            name = "testName",
            email = "test",
            document = "83432616074"
        )
        mockMvc.post("/passengers") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status { isUnprocessableEntity() }
            content { string(CoreMatchers.containsString("Invalid email")) }
        }
    }

    @Test
    fun executeGetPassengersWithSuccess() {
        val request = CreatePassengersRequest(
            name = "testName",
            email = "test@email.com",
            document = "83432616074"
        )
        val result = mockMvc.post("/passengers") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andReturn().response.contentAsString.substringAfter("{\"passengerId\":\"").substringBefore("\"}")

        mockMvc.get("/passengers/${result}") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status { isOk() }
            content { string(CoreMatchers.containsString("name\":\"testName\",\"email\":\"test@email.com\",\"document\":\"83432616074\"")) }
        }
    }
}