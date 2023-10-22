package com.branas.cleanarch.infra.request

import java.beans.ConstructorProperties

data class RideCalculatorRequest @ConstructorProperties("positions") constructor(val positions: List<RidePositionRequest>)