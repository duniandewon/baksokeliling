package com.example.home.presentation.map

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.domain.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel()
class MapViewModel @Inject constructor(private val logoutUseCase: LogoutUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow<MapUiState>(MapUiState.Map())
    val uiState: StateFlow<MapUiState> = _uiState

    fun onEvent(uiEvent: MapUiEvent) {
        when (uiEvent) {
            MapUiEvent.Logout -> {
                lgoOut()
            }

            else -> {
                Unit
            }
        }
    }

    private fun lgoOut() = viewModelScope.launch {
        val ui = (_uiState.value as MapUiState.Map) ?: return@launch
        logoutUseCase.invoke()
        _uiState.value = MapUiState.HasLoggedOut
    }
}