package com.example.auth.presentation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.auth.presentation.login.LoginScreen
import com.example.auth.presentation.splash.SplashScreen
import kotlinx.serialization.Serializable

@Serializable
object Auth

@Serializable
object Splash

@Serializable
object Login

fun NavGraphBuilder.authNavGraph(navController: NavController, onAuthSuccess: () -> Unit) {
    navigation<Auth>(startDestination = Splash) {
        composable<Splash> {
            SplashScreen(viewModel = hiltViewModel(), navController = navController) {
                onAuthSuccess()
            }
        }

        composable<Login> {
            LoginScreen(viewModel = hiltViewModel()) {
                onAuthSuccess()
            }
        }
    }
}