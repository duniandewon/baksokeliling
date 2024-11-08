package com.example.common.di

import com.example.common.data.datasource.dao.TokenManagement
import com.example.common.data.datasource.database.TokenManagementImpl
import com.example.common.data.repository.CmnAuthRepositoryImpl
import com.example.common.domain.repository.CmnAuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class CommonModule {
    @Provides
    fun provideTokenManager(impl: TokenManagementImpl): TokenManagement = impl

    @Provides
    fun provideCmnAuthRepositoryImpl(impl: CmnAuthRepositoryImpl): CmnAuthRepository = impl
}