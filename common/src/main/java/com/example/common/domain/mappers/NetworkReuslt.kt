package com.example.common.domain.mappers

import com.example.common.domain.models.Resource
import com.example.common.domain.models.ResourceError
import com.example.network.NetworkException
import com.example.network.NetworkResult

fun NetworkResult.Error<*>.toResourceError(): Resource.Error {
    return when (exception) {
        is NetworkException.NotFoundException -> Resource.Error(ResourceError.SERVICE_UNAVAILABLE)
        is NetworkException.UnauthorizedException -> Resource.Error(ResourceError.UNAUTHORIZED)
        else -> {
            Resource.Error(ResourceError.UNKNOWN)
        }
    }
}