package com.branas.refactoring.controller.request

data class CreateDriversRequest(val name: String, val email: String, val document: String, val carPlate: String)