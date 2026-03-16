package com.solarlyz.appsubastas.features.auth.data.remote.models

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("name") val name: String,
    @SerializedName("lastname") val lastName: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)

data class LoginRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)

// La API de Go solo devuelve el token en el login
data class AuthResponse(
    @SerializedName("token") val token: String
)
