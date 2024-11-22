package ru.sandbox.minitales.auth.data

import ru.sandbox.minitales.network.NetworkResult
import ru.sandbox.minitales.network.RequestHandler
import ru.sandbox.minitales.network.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val requestHandler: RequestHandler
) : UserRepository {
    override suspend fun user(): NetworkResult<Response<UserApiModel>> =
        requestHandler.get(urlParameters = listOf("user"))
}