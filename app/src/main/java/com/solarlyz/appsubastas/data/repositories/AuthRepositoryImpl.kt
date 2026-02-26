package com.solarlyz.appsubastas.data.repositories

import com.solarlyz.appsubastas.domain.repositories.AuthRepository
import com.solarlyz.appsubastas.core.utils.Result
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
) : AuthRepository {
    override suspend fun login(email: String, pass: String) = flow {
        emit(Result.Loading)
        try {
            emit(Result.Success("Token_Fake_123"))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Error desconocido"))
        }
    }
}