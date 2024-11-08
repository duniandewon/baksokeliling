package com.example.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.domain.usecase.LoginUseCase
import com.example.common.domain.models.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Login())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onEvent(uiEvent: LoginUiEvent) {
        when (uiEvent) {
            is LoginUiEvent.PolicyAgreementChanged -> {
                updateSate { it.copy(isPolicyAgreementChecked = uiEvent.isChecked) }
            }

            is LoginUiEvent.RoleChanged -> {
                updateSate { it.copy(role = uiEvent.role) }
            }

            is LoginUiEvent.UsernameChanged -> {
                updateSate { it.copy(username = uiEvent.username) }
            }

            LoginUiEvent.Login -> {
                login()
            }

            else -> {
                Unit
            }
        }
    }

    private fun updateSate(update: (LoginUiState.Login) -> LoginUiState.Login) {
        _uiState.value = (_uiState.value as? LoginUiState.Login)?.let(update)
            ?: _uiState.value
    }

    fun login() = viewModelScope.launch {
        val ui = (_uiState.value as? LoginUiState.Login) ?: return@launch
        _uiState.value = when (val loginResult =
            loginUseCase.invoke(role = ui.role, username = ui.username)) {
            is Resource.Success -> {
                LoginUiState.Authenticated
            }

            is Resource.Error -> {
                LoginUiState.Login()
            }
        }
    }
}