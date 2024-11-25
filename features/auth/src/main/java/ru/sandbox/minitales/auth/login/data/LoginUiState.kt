package ru.sandbox.minitales.auth.login.data

import androidx.annotation.StringRes

data class LoginUiState(
    val email: String = "",
    @StringRes val emailError: Int? = null,
    val password: String = "",
    @StringRes val passwordError: Int? = null
)