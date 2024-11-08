package com.example.auth.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthModel(val token: String)
