package com.example.home.domain.usecase

import com.example.home.domain.repository.LocationRepository
import com.example.home.domain.model.UserLocation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserLocationUseCase @Inject constructor(
    private val repository: LocationRepository
) {
    fun invoke(): Flow<UserLocation> = repository.getCurrentLocation()
}