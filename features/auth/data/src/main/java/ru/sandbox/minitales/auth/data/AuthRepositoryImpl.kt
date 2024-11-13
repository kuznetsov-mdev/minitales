package ru.sandbox.minitales.auth.data

import ru.sandbox.minitales.network.NetworkResult
import ru.sandbox.minitales.network.RequestHandler
import ru.sandbox.minitales.network.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val requestHandler: RequestHandler
) : AuthRepository {

    override suspend fun login(request: UserLoginRequest): NetworkResult<Response<UserApiModel>> {
        return requestHandler.post(
            urlParameters = listOf("auth", "login"),
            body = request
        )
    }
}