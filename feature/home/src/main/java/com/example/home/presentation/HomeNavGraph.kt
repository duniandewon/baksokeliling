package com.example.home.presentation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.home.presentation.map.MapScreen
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Map

fun NavGraphBuilder.homeNavGraph(navController: NavController, onLogout: () -> Unit) {
    navigation<Home>(startDestination = Map) {
        composable<Map> {
            MapScreen(viewModel = hiltViewModel(), onLogout = { onLogout() })
        }
    }
}