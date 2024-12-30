package com.example.home.domain.repository

import com.example.home.domain.model.UserLocation
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getLocationUpdates(): Flow<UserLocation>
    fun getCurrentLocation(): Flow<UserLocation>
}