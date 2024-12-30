package com.example.home.presentation.map

import com.example.home.domain.model.UserLocation

sealed class MapUiState {
    data object GetLocationPermission : MapUiState()
    data class Map(val isLoading: Boolean = true, val myLocation: UserLocation = UserLocation(0.0, 0.0)) :
        MapUiState()
}