package com.example.auth.data.repository

import com.example.auth.data.datasource.entity.AuthCredential
import com.example.auth.data.datasource.services.AuthServices
import com.example.auth.domain.model.AuthModel
import com.example.auth.domain.repository.AuthRepository
import com.example.common.data.datasource.dao.TokenManagement
import com.example.common.domain.mappers.toResourceError
import com.example.common.domain.models.Resource
import com.example.network.NetworkResult
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthServices,
    private val tokenManager: TokenManagement,
    private val mapper: AuthModelMapper
) :
    AuthRepository {
    override suspend fun login(
        username: String,
        role: String
    ): Resource<AuthModel> {
        val credential = AuthCredential(username = username, role = role)

        return when (val result = authApi.login(credential)) {
            is NetworkResult.Error -> {
                result.toResourceError()
            }

            is NetworkResult.Success -> {
                val authModel = mapper.map(result.result.data)
                tokenManager.saveJwtToken(authModel.token)
                Resource.Success(authModel)
            }
        }
    }
}