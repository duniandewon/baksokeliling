package com.example.auth.data.repository

interface Mapper<F, T> {
    fun map(from: F): T
}