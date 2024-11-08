package com.example.auth.presentation.di

import com.example.auth.data.datasource.api.AuthApi
import com.example.auth.data.datasource.services.AuthServices
import com.example.auth.data.repository.AuthRepositoryImpl
import com.example.auth.domain.repository.AuthRepository
import com.example.network.RequestHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class AuthModule {
    @Provides
    fun provideAuthServices(requestHandler: RequestHandler): AuthServices = AuthApi(requestHandler)

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl
}