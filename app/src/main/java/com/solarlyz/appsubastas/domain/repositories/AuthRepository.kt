package com.solarlyz.appsubastas.domain.repositories

import com.solarlyz.appsubastas.core.utils.Result
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, pass: String): Flow<Result<String>>
}