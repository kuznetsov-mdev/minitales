package ru.sandbox.minitales.auth.domain

data class User(
    val avatar: String,
    val createdAt: String,
    val email: String,
    val fullName: String,
    val id: Int,
)
