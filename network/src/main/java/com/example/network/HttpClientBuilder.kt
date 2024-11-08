package com.example.network

import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class HttpClientBuilder {
    private lateinit var protocol: URLProtocol
    private lateinit var host: String
    private  var accessToken: String? = null
    private var port: Int? = null

    fun protocol(protocol: URLProtocol) = apply { this.protocol = protocol }

    fun host(host: String) = apply { this.host = host }

    fun port(port: Int) = apply { this.port = port }

    fun build(): HttpClient = HttpClient(Android) {
        defaultRequest {
            url {
                protocol = this@HttpClientBuilder.protocol
                host = this@HttpClientBuilder.host
                this@HttpClientBuilder.port?.let { port = it }
            }

            header(HttpHeaders.ContentType, "application/json")
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens("", "")
                }

                refreshTokens {
                    BearerTokens("", "")
                }
            }
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }
            }

            level = LogLevel.ALL
        }
    }
}