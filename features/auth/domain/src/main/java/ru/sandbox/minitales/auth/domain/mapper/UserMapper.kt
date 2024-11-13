package ru.sandbox.minitales.auth.domain.mapper

import ru.sandbox.minitales.auth.data.UserApiModel
import ru.sandbox.minitales.auth.domain.User
import javax.inject.Inject

class UserMapper @Inject constructor() : Mapper<UserApiModel, User> {

    override fun map(from: UserApiModel): User {
        return User(
            avatar = from.avatar,
            email = from.email,
            createdAt = from.createdAt,
            fullName = from.fullName,
            id = from.id
        )
    }
}