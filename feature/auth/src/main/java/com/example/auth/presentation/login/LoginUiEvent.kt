package com.example.auth.presentation.login

sealed class LoginUiEvent {
    data class UsernameChanged(val username: String) : LoginUiEvent()
    data class RoleChanged(val role: String) : LoginUiEvent()
    data class PolicyAgreementChanged(val isChecked: Boolean) : LoginUiEvent()

    data object Login : LoginUiEvent()
}