package com.branas.adapters.domain

private const val VALID_EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"

data class Email(val value: String) {
    init {
        if (!isValidEmail()) throw Exception("Invalid email")
    }

    private fun isValidEmail() = this.value.isNotBlank() && this.value.matches(VALID_EMAIL_REGEX.toRegex())
}