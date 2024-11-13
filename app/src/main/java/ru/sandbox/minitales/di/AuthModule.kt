package ru.sandbox.minitales.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.sandbox.minitales.auth.data.AuthRepository
import ru.sandbox.minitales.auth.data.AuthRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
class AuthModule {

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl
}