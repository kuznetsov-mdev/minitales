package ru.sandbox.minitales.auth.validators

import ru.sandbox.minitales.auth.R
import ru.sandbox.minitales.common.utils.validator.InputValidator
import ru.sandbox.minitales.common.utils.validator.ValidationResult

class EmailValidator : InputValidator {

    private val emailPattern = Regex(
        pattern = "^[\\w\\.]+@([\\w\\-]+\\.)+[A-Z]{2,}$",
        option = RegexOption.IGNORE_CASE,
    )

    override fun validate(input: String): ValidationResult {
        return if (input.isBlank()) {
            ValidationResult(R.string.email_cannot_be_empty)
        } else if (emailPattern.matches(input).not()) {
            ValidationResult(R.string.invalid_email)
        } else {
            ValidationResult()
        }
    }
}