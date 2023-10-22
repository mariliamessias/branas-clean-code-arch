package com.branas.cleanarch.infra.request

import java.math.BigDecimal

data class RidePositionRequest(
    val lat: BigDecimal,
    val long: BigDecimal,
    val date: String
)