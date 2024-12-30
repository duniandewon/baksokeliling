package com.example.home.domain.usecase

import com.example.home.domain.model.UserLocation
import com.example.home.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserLocationUpdatesUseCase @Inject constructor(
    private val repository: LocationRepository
) {
    fun invoke(): Flow<UserLocation> = repository.getLocationUpdates()
}