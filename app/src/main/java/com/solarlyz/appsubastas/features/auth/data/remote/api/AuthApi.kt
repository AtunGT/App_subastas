package com.solarlyz.appsubastas.features.auth.data.remote.api

import com.solarlyz.appsubastas.features.auth.data.remote.models.AuthResponse
import com.solarlyz.appsubastas.features.auth.data.remote.models.LoginRequest
import com.solarlyz.appsubastas.features.auth.data.remote.models.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("users/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @POST("users/register")
    suspend fun register(@Body request: RegisterRequest): Response<Unit>
}
