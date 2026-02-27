package com.solarlyz.appsubastas.data.models

data class RegisterRequest(
    val name: String,
    val lastname: String,
    val email: String,
    val password_hash: String
)