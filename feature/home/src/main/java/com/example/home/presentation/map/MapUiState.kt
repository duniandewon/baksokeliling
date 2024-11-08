package com.example.home.presentation.map

sealed class MapUiState {
    data object HasLoggedOut : MapUiState()
    data class Map(val isLoading: Boolean = true, val moveToLogin: Boolean = false) : MapUiState()
}