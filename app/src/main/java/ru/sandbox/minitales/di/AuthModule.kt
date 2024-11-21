package ru.sandbox.minitales.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.sandbox.minitales.auth.data.AuthRepository
import ru.sandbox.minitales.auth.data.AuthRepositoryImpl
import ru.sandbox.minitales.auth.data.UserRepository
import ru.sandbox.minitales.auth.data.UserRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
class AuthModule {

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideUserRepository(impl: UserRepositoryImpl): UserRepository = impl
}