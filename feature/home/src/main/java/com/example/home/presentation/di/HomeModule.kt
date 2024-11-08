package com.example.home.presentation.di

import com.example.home.data.repository.HomeRepositoryImpl
import com.example.home.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class HomeModule {

    @Provides
    fun provideHomeRepository(impl: HomeRepositoryImpl): HomeRepository = impl
}