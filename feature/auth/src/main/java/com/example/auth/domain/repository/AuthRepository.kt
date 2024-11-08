package com.example.auth.domain.repository

import com.example.auth.domain.model.AuthModel
import com.example.common.domain.models.Resource

interface AuthRepository {
    suspend fun login(username: String, role: String): Resource<AuthModel>
}