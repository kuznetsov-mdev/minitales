package ru.sandbox.minitales.auth.validators

import ru.sandbox.minitales.common.utils.validator.InputValidator
import javax.inject.Inject

enum class AuthParam {
    EMAIL,
    PASSWORD
}

class ValidatorFactory @Inject constructor(

) {
    private val validators: Map<AuthParam, InputValidator> = mapOf(
        AuthParam.EMAIL to EmailValidator(),
        AuthParam.PASSWORD to PasswordValidator()
    )

    fun get(param: AuthParam): InputValidator {
        return validators[param] ?: throw IllegalArgumentException("Unknown parameter ${param.name}")
    }
}