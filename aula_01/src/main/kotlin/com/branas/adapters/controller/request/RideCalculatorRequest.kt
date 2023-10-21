package com.branas.adapters.controller.request

import java.beans.ConstructorProperties

data class RideCalculatorRequest @ConstructorProperties("segments") constructor(val segments: List<RideSegmentRequest>)