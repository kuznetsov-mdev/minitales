package ru.sandbox.minitales.auth.validators

import ru.sandbox.minitales.auth.R
import ru.sandbox.minitales.common.utils.validator.InputValidator
import ru.sandbox.minitales.common.utils.validator.ValidationResult

class PasswordValidator : InputValidator {

    override fun validate(input: String): ValidationResult {
        return if (input.length < 6) {
            ValidationResult(R.string.error_password)
        } else {
            ValidationResult()
        }
    }
}