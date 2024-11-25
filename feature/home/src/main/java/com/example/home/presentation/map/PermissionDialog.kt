package com.example.home.presentation.map

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PermissionDialog(
    permissionTextProvider: PermissionTextProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onGoToAppSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                if (isPermanentlyDeclined) onGoToAppSettingsClick() else onOkClick()
            }) {
                Text(text = if (isPermanentlyDeclined) "Grant Permission" else "Ok")
            }
        },
        title = { Text(text = "Permission required") },
        text = { Text(text = permissionTextProvider.getDescription(isPermanentlyDeclined = isPermanentlyDeclined)) }
    )
}