package com.example.home.presentation.map

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun MapScreen(viewModel: MapViewModel, onLogout: () -> Unit) {
    val context = LocalContext.current

    val dialogQueue = viewModel.visiblePermissionDialogQueue

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            viewModel.onPermissionResult(
                permission = Manifest.permission.ACCESS_FINE_LOCATION,
                isGranted = isGranted
            )
        }
    )

    when (PackageManager.PERMISSION_GRANTED) {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) -> {
            MapContent(
                onLogout = { onLogout() })
        }

        else -> {
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
    }
}

@Composable
fun MapContent(onLogout: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(modifier = Modifier.clickable {
            onLogout()
        }, text = "Map Screen")
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
    MapContent(onLogout = {})
}