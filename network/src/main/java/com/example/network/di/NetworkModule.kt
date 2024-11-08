package com.example.network.di

import com.example.network.HttpClientBuilder
import com.example.network.RequestHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.http.URLProtocol

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideHttpClient(): HttpClient =
        HttpClientBuilder().host("jsonplaceholder.typicode.com").protocol(URLProtocol.HTTPS).build()

    @Provides
    fun provideRequestHandler(client: HttpClient): RequestHandler = RequestHandler(client)
}