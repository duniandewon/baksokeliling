package com.example.common.data.repository

import com.example.common.data.datasource.dao.TokenManagement
import com.example.common.domain.repository.CmnAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CmnAuthRepositoryImpl @Inject constructor(private val tokenManagement: TokenManagement) :
    CmnAuthRepository {
    override fun isAuthenticated(): Flow<Boolean> = tokenManagement.getJwtToken().map { token ->
        token.isNotEmpty()
    }
}