package com.example.auth.presentation.login

sealed class LoginUiState {
    data object Authenticated : LoginUiState()

    data class Login(
        val username: String = "",
        val role: String = "",
        val isPolicyAgreementChecked: Boolean = false
    ) : LoginUiState()
}