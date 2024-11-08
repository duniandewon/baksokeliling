package com.example.auth.data.datasource.api

import com.example.auth.data.datasource.entity.AuthCredential
import com.example.auth.data.datasource.entity.AuthData
import com.example.auth.data.datasource.services.AuthServices
import com.example.network.NetworkException
import com.example.network.NetworkResult
import com.example.network.RequestHandler
import com.example.network.Response
import kotlinx.coroutines.delay
import javax.inject.Inject

class AuthApi @Inject constructor(private val requestHandler: RequestHandler) : AuthServices {
//    override suspend fun login(credential: AuthCredential): NetworkResult<Response<AuthData>> =
//        requestHandler.post(url = "/login", body = credential)

    override suspend fun login(credential: AuthCredential): NetworkResult<Response<AuthData>> {
        delay(1000)

        val response = Response(data = AuthData("${credential.username}-${credential.role}"))
        return NetworkResult.Success(response)

//        val username = "ndewon"
//        val password = "Password@1"

//        val correctLogin = credential.username == username && credential.password == password
//        delay(1000)
//
//        return if (correctLogin) {
//            val fakeAuthData = AuthData(token = "login-$username")
//
//            val response = Response(data = fakeAuthData, message = "Login successful")
//
//            NetworkResult.Success(response)
//        } else {
//            NetworkResult.Error(
//                NetworkException.UnauthorizedException(
//                    message = "Invalid username or password",
//                    cause = Throwable("Incorrect credentials provided")
//                )
//            )
//        }
    }
}