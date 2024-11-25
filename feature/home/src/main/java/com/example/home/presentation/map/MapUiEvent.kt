package com.example.home.presentation.map

sealed class MapUiEvent {
    data object GetLocationPermission : MapUiEvent()
    data object DismissDialog : MapUiEvent()
    data class PermissionResult(val permission: String, val isGranted: Boolean) : MapUiEvent()
    data object Map : MapUiEvent()
    data object Logout : MapUiEvent()
}