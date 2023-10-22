package com.branas.cleanarch.configuration

import com.branas.cleanarch.domain.fare.chainOfResponsability.FareCalculatorHandler
import com.branas.cleanarch.domain.fare.chainOfResponsability.NormalFareCalculatorHandler
import com.branas.cleanarch.domain.fare.chainOfResponsability.OvernightFareCalculatorHandler
import com.branas.cleanarch.domain.fare.chainOfResponsability.SundayFareCalculatorHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Beans {

    @Bean
    fun fareCalculatorHandler(): FareCalculatorHandler {
        val overnightSundayFareCalculator = OvernightFareCalculatorHandler()
        val sundayFareCalculator = SundayFareCalculatorHandler(overnightSundayFareCalculator)
        val overnightFareCalculator = OvernightFareCalculatorHandler(sundayFareCalculator)
        return NormalFareCalculatorHandler(overnightFareCalculator)
    }
}