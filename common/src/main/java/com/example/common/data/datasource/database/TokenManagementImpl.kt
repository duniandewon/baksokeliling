package com.example.common.data.datasource.database

import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.common.data.datasource.dao.TokenManagement
import com.example.storage.DataStoreHandler
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TokenManagementImpl @Inject constructor(private val dataStoreHandler: DataStoreHandler) :
    TokenManagement {
    private val JWT_TOKEN_KEY = stringPreferencesKey("jwt_token")

    override suspend fun saveJwtToken(token: String) {
        dataStoreHandler.saveValue(JWT_TOKEN_KEY, token)
    }

    override fun getJwtToken(): Flow<String> = dataStoreHandler.getValue(JWT_TOKEN_KEY, "")

    override suspend fun deleteToken() {
        dataStoreHandler.deleteValue(JWT_TOKEN_KEY)
    }

}