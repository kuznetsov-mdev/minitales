package ru.sandbox.minitales.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.http.URLProtocol
import ru.sandbox.minitales.BuildConfig
import ru.sandbox.minitales.network.MiniTalesHttpClientBuilder
import ru.sandbox.minitales.network.RequestHandler

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideHttpClient(): HttpClient =
        MiniTalesHttpClientBuilder()
            .protocol(URLProtocol.HTTP)
            .host(BuildConfig.MINI_TALES_HOST)
            .port(8080)
            .build()

    @Provides
    fun provideRequestHandler(client: HttpClient): RequestHandler = RequestHandler(client)
}