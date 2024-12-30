package com.example.home.presentation.map

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.domain.model.UserLocation
import com.example.home.domain.usecase.GetUserLocationUpdatesUseCase
import com.example.home.domain.usecase.GetUserLocationUseCase
import com.example.home.domain.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel()
class MapViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val useLocationUpdateUseCase: GetUserLocationUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<MapUiState>(MapUiState.Map())
    val uiState: StateFlow<MapUiState> = _uiState

    val visiblePermissionDialogQueue = mutableStateListOf<String>()

    fun onPermissionResult(isGranted: Boolean, permission: String) {
        if (!isGranted && !visiblePermissionDialogQueue.contains(permission)) {
            visiblePermissionDialogQueue.add(permission)
        } else {
            onEvent(MapUiEvent.GetLocationsData)
        }
    }

    fun dismissLocationPermissionDialog() {
        visiblePermissionDialogQueue.removeAt(0)
    }

    private fun logoOut() = viewModelScope.launch {
        logoutUseCase.invoke()
        _uiState.value = MapUiState.HasLoggedOut
    }

    private fun getMyLastKnownLocation() {
        viewModelScope.launch {
            useLocationUpdateUseCase.invoke().collect { location ->
                val myLocation =
                    UserLocation(latitude = location.latitude, longitude = location.longitude)
                _uiState.value = MapUiState.Map(isLoading = false, myLocation = myLocation)
            }
        }
    }

    fun onEvent(uiEvent: MapUiEvent) {
        when (uiEvent) {
            is MapUiEvent.GetLocationPermission -> {
                _uiState.value = MapUiState.GetLocationPermission
            }

            is MapUiEvent.GetLocationsData -> {
                getMyLastKnownLocation()
            }

            is MapUiEvent.Logout -> {
                logoOut()
            }

            else -> {}
        }
    }
}