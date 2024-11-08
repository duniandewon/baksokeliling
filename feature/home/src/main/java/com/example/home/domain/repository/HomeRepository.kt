package com.example.home.domain.repository

interface HomeRepository {
    suspend fun logout()

    /**
     * 1. Get users based on roles: seller or customer
     * 2. Logout
     */
}