package com.example.auth.presentation.splash

sealed class SplashUiState {
    data object Authenticated : SplashUiState()
    data class Splash(
        val moveToLogin: Boolean = false,
    ) : SplashUiState()
}