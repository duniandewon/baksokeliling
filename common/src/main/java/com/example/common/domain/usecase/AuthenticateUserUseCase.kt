package com.example.common.domain.usecase

import com.example.common.domain.repository.CmnAuthRepository
import javax.inject.Inject

class AuthenticateUserUseCase @Inject constructor(private val repository: CmnAuthRepository) {
    fun invoke() = repository.isAuthenticated()
}