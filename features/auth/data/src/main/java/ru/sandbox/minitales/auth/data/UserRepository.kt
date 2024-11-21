package ru.sandbox.minitales.auth.data

import ru.sandbox.minitales.network.NetworkResult
import ru.sandbox.minitales.network.Response

interface UserRepository {
    suspend fun user(): NetworkResult<Response<UserApiModel>>
}