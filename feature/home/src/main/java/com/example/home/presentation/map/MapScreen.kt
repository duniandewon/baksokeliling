package com.example.home.presentation.map

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun MapScreen(viewModel: MapViewModel, onLogout: () -> Unit) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    when (val state = uiState.value) {
        MapUiState.HasLoggedOut -> {
            LaunchedEffect(Unit) {
                onLogout()
            }
        }

        is MapUiState.Map -> {
            MapContent(onEvent = { viewModel.onEvent(it) }, uiState = state)
        }

        else -> {
            Unit
        }
    }
}

@Composable
fun MapContent(onEvent: (MapUiEvent) -> Unit, uiState: MapUiState.Map) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(modifier = Modifier.clickable {
            onEvent.invoke(MapUiEvent.Logout)
        }, text = "Map Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMapScreen() {
    MapContent(uiState = MapUiState.Map(), onEvent = {})
}