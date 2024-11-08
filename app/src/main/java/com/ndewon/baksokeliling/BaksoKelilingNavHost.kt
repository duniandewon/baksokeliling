package com.ndewon.baksokeliling

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.auth.presentation.Auth
import com.example.auth.presentation.Login
import com.example.auth.presentation.authNavGraph
import com.example.home.presentation.Home
import com.example.home.presentation.homeNavGraph

@Composable
fun BaksoKelilingNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Auth) {
        authNavGraph(navController = navController, onAuthSuccess = {
            navController.navigate(Home) {
                popUpTo(Auth) {
                    inclusive = true
                }
            }
        })

        homeNavGraph(navController = navController, onLogout = {
            navController.navigate(Login) {
                popUpTo<Home>() {
                    inclusive = true
                }
            }
        })
    }
}