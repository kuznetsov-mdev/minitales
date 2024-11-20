package ru.sandbox.minitales.storage

import kotlinx.coroutines.flow.Flow

data class CurrentUser(
    val id: Int,
    val authKey: String
)

interface SessionHandler {
    suspend fun setCurrentUser(id: Int, authKey: String)

    fun getCurrentUser(): Flow<CurrentUser>

    suspend fun clear()
}