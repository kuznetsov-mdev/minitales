package ru.sandbox.minitales.common.utils.validator

interface InputValidator {
    fun validate(input: String): ValidationResult
}