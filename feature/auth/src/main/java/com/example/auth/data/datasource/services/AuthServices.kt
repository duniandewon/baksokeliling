package com.example.auth.data.datasource.services

import com.example.auth.data.datasource.entity.AuthCredential
import com.example.auth.data.datasource.entity.AuthData
import com.example.network.NetworkResult
import com.example.network.Response

interface AuthServices {
    suspend fun login(credential: AuthCredential): NetworkResult<Response<AuthData>>
}