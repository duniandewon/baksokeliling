package com.example.auth.presentation.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.domain.usecase.AuthenticateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val isLoggedIn: AuthenticateUserUseCase) :
    ViewModel() {

    init {
        checkIsLoggedIn()
    }

    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.Splash())
    val uiState: StateFlow<SplashUiState> = _uiState

    private fun checkIsLoggedIn() = viewModelScope.launch {
        delay(5000)
        isLoggedIn.invoke().collect { isAuthenticated ->
            _uiState.value =
                if (isAuthenticated) SplashUiState.Authenticated else SplashUiState.Splash(
                    moveToLogin = true
                )

        }
    }
}