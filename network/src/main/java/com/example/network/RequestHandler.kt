package com.example.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.prepareRequest
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.appendPathSegments
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RequestHandler(val httpClient: HttpClient) {
    suspend inline fun <reified T> handleRequest(
        request: () -> T
    ): NetworkResult<T> = try {
        val response = request()
        NetworkResult.Success(response)
    } catch (e: Exception) {
        val networkException = if (e is ResponseException) {
            val errorBody = e.response.body<DefaultError>()
            when (e.response.status) {
                HttpStatusCode.Unauthorized -> NetworkException.UnauthorizedException(
                    errorBody.message,
                    e
                )

                HttpStatusCode.NotFound -> NetworkException.NotFoundException(
                    "API Not found", e
                )

                else -> NetworkException.UnknownException(
                    "Unexpected error: ${e.response.status}", e
                )
            }
        } else {
            NetworkException.UnknownException(e.message ?: "Unknown error", e)
        }
        NetworkResult.Error(networkException)
    }

    suspend inline fun <reified R> get(
        url: String,
        queryParams: Map<String, String> = emptyMap(),
        headers: Map<String, String> = emptyMap()
    ): NetworkResult<R> = handleRequest {
        httpClient.get(url) {
            headers.forEach { (key, value) -> header(key, value) }
            queryParams.forEach { (key, value) -> parameter(key, value) }
        }.body()
    }

    suspend inline fun <reified R, reified T> post(
        url: String,
        body: T,
        headers: Map<String, String> = emptyMap(),
        queryParams: Map<String, String> = emptyMap()
    ): NetworkResult<R> = handleRequest {
        httpClient.post(url) {
            headers.forEach { (key, value) -> header(key, value) }
            queryParams.forEach { (key, value) -> parameter(key, value) }
            setBody(body)
        }.body()
    }
}