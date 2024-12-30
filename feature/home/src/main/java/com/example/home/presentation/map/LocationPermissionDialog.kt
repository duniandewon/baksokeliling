package com.example.home.presentation.map


class LocationPermissionDialog : PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String =
        if (isPermanentlyDeclined) "You have permanently declined location permission. In order to use this app, you need to grant the location permission in the settings."
        else "In order to use this app, you need to grant the location permission."
}