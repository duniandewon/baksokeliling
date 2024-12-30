package com.example.home.presentation.map

sealed class MapUiEvent {
    data object GetLocationPermission : MapUiEvent()
    data object GetLocationsData : MapUiEvent()
    data object Map : MapUiEvent()
    data object Logout : MapUiEvent()
}