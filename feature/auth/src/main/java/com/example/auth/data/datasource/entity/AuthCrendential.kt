package com.example.auth.data.datasource.entity

import kotlinx.serialization.Serializable

@Serializable
data class AuthCredential(val username: String, val role: String)
