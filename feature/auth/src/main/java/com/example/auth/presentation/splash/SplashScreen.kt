package com.example.auth.presentation.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.auth.presentation.Login

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    navController: NavController,
    onAuthSuccess: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState.value) {
        SplashUiState.Authenticated -> {
            LaunchedEffect(Unit) { onAuthSuccess() }
        }

        is SplashUiState.Splash -> {
            if (state.moveToLogin) {
                LaunchedEffect(Unit) {
                    navController.navigate(Login) {
                        popUpTo(Login) {
                            inclusive = true
                        }
                    }
                }
            } else {
                Splash(state)
            }
        }

        else -> {
            Unit
        }
    }
}

@Composable
fun Splash(state: SplashUiState.Splash) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bakso Keliling Logo")
    }
}