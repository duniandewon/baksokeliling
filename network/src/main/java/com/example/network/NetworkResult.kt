package com.example.network

sealed interface NetworkResult<T> {
    data class Success<T>(val result: T) : NetworkResult<T>
    data class Error<T>(val exception: Exception) :
        NetworkResult<T>
}