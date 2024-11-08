package com.example.auth.domain.usecase

import com.example.auth.domain.model.AuthModel
import com.example.auth.domain.repository.AuthRepository
import com.example.common.domain.models.Resource
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend fun invoke(username: String, role: String): Resource<AuthModel> {
        return repository.login(username, role)
    }
}