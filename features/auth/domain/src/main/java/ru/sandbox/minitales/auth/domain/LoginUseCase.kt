package ru.sandbox.minitales.auth.domain

import ru.sandbox.minitales.auth.data.AuthRepository
import ru.sandbox.minitales.auth.data.UserLoginRequest
import ru.sandbox.minitales.auth.domain.mapper.UserMapper
import ru.sandbox.minitales.network.NetworkException
import ru.sandbox.minitales.network.NetworkResult
import ru.sandbox.minitales.storage.SessionHandler
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val sessionHandler: SessionHandler,
    private val mapper: UserMapper
) {
    suspend fun invoke(email: String, password: String): Resource<User> {
        val request = UserLoginRequest(email, password)

        return when (val result = authRepository.login(request)) {
            is NetworkResult.Error -> result.toResourceError()
            is NetworkResult.Success -> {
                sessionHandler.setCurrentUser(result.result.data.id, result.result.data.authToken)
                Resource.Success(mapper.map(result.result.data))
            }

        }
    }
}

fun NetworkResult.Error<*>.toResourceError(): Resource.Error {
    return when (exception) {
        is NetworkException.NotFoundException -> Resource.Error(ResourceError.SERVICE_UNAVAILABLE)
        is NetworkException.UnauthorizedException -> Resource.Error(ResourceError.UNAUTHORIZED)
        else -> Resource.Error(ResourceError.UNKNOWN)
    }
}