package com.branas.cleanarch.controller.request

import java.beans.ConstructorProperties

data class RideCalculatorRequest @ConstructorProperties("segments") constructor(val segments: List<RideSegmentRequest>)