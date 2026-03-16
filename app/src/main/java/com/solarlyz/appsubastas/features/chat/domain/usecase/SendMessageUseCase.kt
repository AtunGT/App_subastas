package com.solarlyz.appsubastas.features.chat.domain.usecase

import com.solarlyz.appsubastas.features.chat.domain.repository.MessageRepository
import com.solarlyz.appsubastas.core.utils.Result
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val repository: MessageRepository
) {
    suspend operator fun invoke(text: String): Result<Unit> {
        if (text.isBlank()) return Result.Error("El mensaje no puede estar vacío")
        return repository.sendMessage(text)
    }
}
