package com.example.home.presentation.map

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.domain.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel()
class MapViewModel @Inject constructor(private val logoutUseCase: LogoutUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow<MapUiState>(MapUiState.Map())
    val uiState: StateFlow<MapUiState> = _uiState

    val visiblePermissionDialogQueue = mutableStateListOf<String>()

    fun onPermissionResult(isGranted: Boolean, permission: String) {
        if (!isGranted && !visiblePermissionDialogQueue.contains(permission)) {
            visiblePermissionDialogQueue.add(permission)
        } else {
            // TODO: fetch users location data
        }
    }

    fun dismissLocationPermissionDialog() {
        visiblePermissionDialogQueue.removeFirst()
    }

    private fun lgoOut() = viewModelScope.launch {
        logoutUseCase.invoke()
    }
}