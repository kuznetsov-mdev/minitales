package ru.sandbox.minitales.auth.data

import kotlinx.serialization.Serializable

@Serializable
data class UserApiModel(
    val authToken: String? = null,
    val avatar: String,
    val createdAt: String,
    val email: String,
    val fullName: String,
    val id: Int,
)
