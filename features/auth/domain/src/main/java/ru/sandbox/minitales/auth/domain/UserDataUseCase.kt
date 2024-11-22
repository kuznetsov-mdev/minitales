package ru.sandbox.minitales.auth.domain

import ru.sandbox.minitales.auth.data.UserRepository
import ru.sandbox.minitales.auth.domain.mapper.UserMapper
import ru.sandbox.minitales.network.NetworkResult
import javax.inject.Inject

class UserDataUseCase @Inject constructor(
    private val repository: UserRepository,
    private val mapper: UserMapper
) {

    suspend fun invoke(): Resource<User> {
        return when (val result = repository.user()) {
            is NetworkResult.Error -> result.toResourceError()
            is NetworkResult.Success -> Resource.Success(mapper.map(result.result.data))
        }
    }
}