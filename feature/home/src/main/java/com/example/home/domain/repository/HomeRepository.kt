package com.example.home.domain.repository

import com.example.home.domain.model.UserLocation
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun logout()
}