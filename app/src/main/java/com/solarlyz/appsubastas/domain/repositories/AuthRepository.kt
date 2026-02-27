package com.solarlyz.appsubastas.domain.repositories

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(name: String, lastname: String, email: String, pass: String): Flow<Result<Unit>>
    suspend fun login(email: String, pass: String): Flow<Result<Unit>>
}