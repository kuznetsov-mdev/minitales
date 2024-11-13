package ru.sandbox.minitales.auth.data

import ru.sandbox.minitales.network.NetworkResult
import ru.sandbox.minitales.network.Response

interface AuthRepository {
    suspend fun login(request: UserLoginRequest): NetworkResult<Response<UserApiModel>>
}