package com.branas.cleanarch.infra.request

data class CreateDriversRequest(val name: String, val email: String, val document: String, val carPlate: String)