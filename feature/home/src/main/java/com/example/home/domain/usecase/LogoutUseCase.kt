package com.example.home.domain.usecase

import com.example.home.domain.repository.HomeRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val repository: HomeRepository) {
    suspend fun invoke() {
        repository.logout()
    }
}