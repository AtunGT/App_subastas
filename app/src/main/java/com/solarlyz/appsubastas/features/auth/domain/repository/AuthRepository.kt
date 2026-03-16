package com.solarlyz.appsubastas.features.auth.domain.repository

import com.solarlyz.appsubastas.features.auth.domain.model.User
import com.solarlyz.appsubastas.core.utils.Result

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User>
    suspend fun register(firstName: String, lastName: String, email: String, password: String): Result<User>
    suspend fun logout()
    suspend fun getAccessToken(): String?
}
