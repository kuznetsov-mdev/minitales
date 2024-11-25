package ru.sandbox.minitales.common.utils.validator

data class ValidationResult(
    val errorMessage: String?
) {
    val isValid: Boolean
        get() = errorMessage == null
}