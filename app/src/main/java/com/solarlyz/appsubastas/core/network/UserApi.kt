package com.solarlyz.appsubastas.core.network

import com.solarlyz.appsubastas.data.models.LoginRequest
import com.solarlyz.appsubastas.data.models.LoginResponse
import com.solarlyz.appsubastas.data.models.RegisterRequest
import com.solarlyz.appsubastas.data.models.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("api/users/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("api/users/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>
}