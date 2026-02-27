package com.solarlyz.appsubastas.domain.usecases

import com.solarlyz.appsubastas.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(name: String, lastname: String, email: String, pass: String): Flow<Result<Unit>> {
        return repository.register(name, lastname, email, pass)
    }
}