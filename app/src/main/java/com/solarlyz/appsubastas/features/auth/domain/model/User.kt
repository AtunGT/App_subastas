package com.solarlyz.appsubastas.features.auth.domain.model

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val token: String? = null
)
