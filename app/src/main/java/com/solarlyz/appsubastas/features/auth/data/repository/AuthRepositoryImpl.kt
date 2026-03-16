package com.solarlyz.appsubastas.features.auth.data.repository

import android.content.Context
import com.solarlyz.appsubastas.core.utils.Result
import com.solarlyz.appsubastas.features.auth.data.remote.api.AuthApi
import com.solarlyz.appsubastas.features.auth.data.remote.models.LoginRequest
import com.solarlyz.appsubastas.features.auth.data.remote.models.RegisterRequest
import com.solarlyz.appsubastas.features.auth.domain.model.User
import com.solarlyz.appsubastas.features.auth.domain.repository.AuthRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    @ApplicationContext private val context: Context
) : AuthRepository {

    private val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    override suspend fun login(email: String, password: String): Result<User> {
        return try {
            val response = authApi.login(LoginRequest(email, password))
            if (response.isSuccessful && response.body() != null) {
                val authResponse = response.body()!!
                saveToken(authResponse.token)
                // Como la API solo devuelve el token, creamos un objeto User parcial
                Result.Success(User(id = 0, firstName = "", lastName = "", email = email, token = authResponse.token))
            } else {
                Result.Error("Credenciales incorrectas")
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: "Error de red")
        }
    }

    override suspend fun register(firstName: String, lastName: String, email: String, password: String): Result<User> {
        return try {
            val response = authApi.register(RegisterRequest(firstName, lastName, email, password))
            if (response.isSuccessful) {
                Result.Success(User(id = 0, firstName = firstName, lastName = lastName, email = email, token = null))
            } else {
                Result.Error("Error al registrar usuario: ${response.code()}")
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: "Error de red")
        }
    }

    override suspend fun logout() {
        prefs.edit().remove("access_token").apply()
    }

    override suspend fun getAccessToken(): String? {
        return prefs.getString("access_token", null)
    }

    private fun saveToken(token: String) {
        prefs.edit().putString("access_token", token).apply()
    }
}
