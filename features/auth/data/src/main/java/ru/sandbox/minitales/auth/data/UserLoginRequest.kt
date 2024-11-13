package ru.sandbox.minitales.auth.data

data class UserLoginRequest(
    val email: String,
    val password: String
)
