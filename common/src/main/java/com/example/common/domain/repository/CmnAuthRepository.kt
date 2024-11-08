package com.example.common.domain.repository

import kotlinx.coroutines.flow.Flow

interface CmnAuthRepository {
    fun isAuthenticated(): Flow<Boolean>
}
