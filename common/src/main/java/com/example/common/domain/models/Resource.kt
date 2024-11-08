package com.example.common.domain.models

sealed interface Resource<out T> {
    data class Error(val e: ResourceError) : Resource<Nothing>
    data class Success<R>(val result: R) : Resource<R>
}