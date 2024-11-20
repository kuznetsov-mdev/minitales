package ru.sandbox.minitales.storage

import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream

data object UserSerializer : Serializer<User> {
    override val defaultValue: User
        get() = User.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): User {
        return User.parseFrom(input)
    }

    override suspend fun writeTo(t: User, output: OutputStream) {
        t.writeTo(output)
    }
}