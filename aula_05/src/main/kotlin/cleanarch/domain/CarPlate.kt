package com.branas.cleanarch.domain

private const val VALID_CAR_PLATE_REGEX = "^[A-Za-z]{3}[0-9]{4}"

data class CarPlate(val value: String) {
    init {
        if (!this.validate()) throw Exception("Invalid car plate")
    }

    private fun validate(): Boolean {
        return this.value.isNotBlank() && this.value.matches(VALID_CAR_PLATE_REGEX.toRegex())
    }
}