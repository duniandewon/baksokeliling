package com.example.home.presentation.map

sealed class MapUiState {
    data object HasLoggedOut : MapUiState()
    data object GetLocationPermission : MapUiState()
    data object HasNoLocationPermissions : MapUiState()
    data object LocationPermissionDenied : MapUiState()
    data class Map(val isLoading: Boolean = true, val hasLocationPermission: Boolean = false) :
        MapUiState()
}