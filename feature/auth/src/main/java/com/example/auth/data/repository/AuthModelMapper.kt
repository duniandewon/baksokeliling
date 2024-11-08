package com.example.auth.data.repository

import com.example.auth.data.datasource.entity.AuthData
import com.example.auth.domain.model.AuthModel
import javax.inject.Inject

class AuthModelMapper @Inject constructor() : Mapper<AuthData, AuthModel> {
    override fun map(from: AuthData): AuthModel {
        return AuthModel(token = from.token)
    }
}