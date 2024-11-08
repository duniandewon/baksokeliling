package com.example.network

import kotlinx.serialization.Serializable

@Serializable
data class Response<T>(val data: T, val message: String? = null)