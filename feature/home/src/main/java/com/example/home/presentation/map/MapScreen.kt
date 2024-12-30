package com.example.home.presentation.map

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapScreen(viewModel: MapViewModel, onLogout: () -> Unit) {
    val context = LocalContext.current

    val dialogQueue = viewModel.visiblePermissionDialogQueue

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            viewModel.onPermissionResult(
                permission = Manifest.permission.ACCESS_FINE_LOCATION,
                isGranted = isGranted
            )
        }
    )

    LifecycleResumeEffect(Unit) {
        if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            viewModel.onEvent(MapUiEvent.GetLocationPermission)
        } else {
            viewModel.onEvent(MapUiEvent.GetLocationsData)
        }

        onPauseOrDispose {
        }
    }

    when (val state = uiState.value) {
        is MapUiState.HasLoggedOut -> {
            LaunchedEffect(Unit) {
                onLogout()
            }
        }

        is MapUiState.GetLocationPermission -> {
            LaunchedEffect(Unit) {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }

            dialogQueue.reversed().forEach { permission ->
                PermissionDialog(
                    permissionTextProvider = LocationPermissionDialog(),
                    isPermanentlyDeclined = !ActivityCompat.shouldShowRequestPermissionRationale(
                        context as Activity,
                        permission
                    ),
                    onDismiss = viewModel::dismissLocationPermissionDialog,
                    onOkClick = {
                        viewModel.dismissLocationPermissionDialog()
                        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

                    },
                    onGoToAppSettingsClick = {
                        viewModel.dismissLocationPermissionDialog()
                        openAppSettings(context)
                    }
                )
            }
        }

        is MapUiState.Map -> {
            MapContent(
                onEvent = { viewModel.onEvent(it) },
                uiState = state
            )
        }
    }
}

@Composable
fun MapContent(onEvent: (MapUiEvent) -> Unit, uiState: MapUiState.Map) {
    val location = LatLng(uiState.myLocation.latitude, uiState.myLocation.longitude)

    val markerState = rememberMarkerState()

    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(location) {
        markerState.position = location
        cameraPositionState.position = CameraPosition.fromLatLngZoom(location, 13.2f)
    }

    if (uiState.isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Loading...")
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                Marker(state = markerState, title = "Me")
            }

            FloatingActionButton(
                onClick = { onEvent.invoke(MapUiEvent.Logout) },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Text(text = "logout")
            }
        }
    }
}

fun openAppSettings(context: Activity) {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", context.packageName, null)
    ).also { context.startActivity(it) }
}

@Preview(showBackground = true)
@Composable
fun PreviewMapScreen() {
    MapContent(onEvent = {}, uiState = MapUiState.Map())
}