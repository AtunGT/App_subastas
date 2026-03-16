package com.solarlyz.appsubastas.features.chat.domain.repository

import com.solarlyz.appsubastas.features.chat.domain.model.Message
import com.solarlyz.appsubastas.core.utils.Result
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    fun getMessages(): Flow<List<Message>>
    suspend fun sendMessage(text: String): Result<Unit>
}
