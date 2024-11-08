package com.example.home.data.repository

import com.example.common.data.datasource.dao.TokenManagement
import com.example.home.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val tokenManagement: TokenManagement) :
    HomeRepository {
    override suspend fun logout() {
        tokenManagement.deleteToken()
    }
}