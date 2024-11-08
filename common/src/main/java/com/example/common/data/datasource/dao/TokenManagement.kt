package com.example.common.data.datasource.dao

import kotlinx.coroutines.flow.Flow

interface TokenManagement {
    suspend fun saveJwtToken(token: String)
    fun getJwtToken(): Flow<String>
    suspend fun deleteToken()
}