package com.solarlyz.appsubastas.domain.usecases

import com.solarlyz.appsubastas.domain.repositories.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, pass: String) = repository.login(email, pass)
}