package com.example.home.presentation.map

interface PermissionTextProvider {
    fun getDescription(isPermanentlyDeclined: Boolean): String
}