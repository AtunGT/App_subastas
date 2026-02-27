package com.solarlyz.appsubastas.data.repositories

import com.solarlyz.appsubastas.core.network.AuthApi
import com.solarlyz.appsubastas.data.models.LoginRequest
import com.solarlyz.appsubastas.data.models.RegisterRequest
import com.solarlyz.appsubastas.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {

    override suspend fun login(email: String, pass: String): Flow<Result<Unit>> = flow {
        try {
            val response = authApi.login(LoginRequest(email, pass))
            if (response.isSuccessful) {

                emit(Result.success(Unit))
            } else {
                emit(Result.failure(Exception("Correo o contraseña incorrectos")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun register(
        name: String,
        lastname: String,
        email: String,
        pass: String
    ): Flow<Result<Unit>> = flow {
        try {
            val response = authApi.register(RegisterRequest(name, lastname, email, pass))
            if (response.isSuccessful) {
                emit(Result.success(Unit))
            } else {
                emit(Result.failure(Exception("No se pudo completar el registro")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}