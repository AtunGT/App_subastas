package com.solarlyz.appsubastas.features.chat.data.repository

import com.solarlyz.appsubastas.core.data.local.dao.MessageDao
import com.solarlyz.appsubastas.core.data.local.entities.MessageEntity
import com.solarlyz.appsubastas.core.utils.Result
import com.solarlyz.appsubastas.features.chat.data.mapper.toDomain
import com.solarlyz.appsubastas.features.chat.data.remote.WebSocketManager
import com.solarlyz.appsubastas.features.chat.domain.model.Message
import com.solarlyz.appsubastas.features.chat.domain.repository.MessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageRepositoryImpl @Inject constructor(
    private val messageDao: MessageDao,
    private val webSocketManager: WebSocketManager
) : MessageRepository {

    override fun getMessages(): Flow<List<Message>> {
        return messageDao.getAllMessages().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun sendMessage(text: String): Result<Unit> = withContext(Dispatchers.IO) {
        val tempId = UUID.randomUUID().toString()
        val optimisticMessage = MessageEntity(
            id = tempId,
            text = text,
            timestamp = System.currentTimeMillis(),
            isOptimistic = true
        )

        try {
            // 1. Inserción Optimista en DB Local
            messageDao.insert(optimisticMessage)

            // 2. Intento de envío vía WebSocket
            val success = webSocketManager.sendMessage(text)

            if (success) {
                // El servidor responderá vía WS y el Manager actualizará la DB eliminando el flag optimistic
                // En una implementación real, el server debería devolver el mismo tempId para machear
                // o el cliente debería manejar el reemplazo. Por simplicidad en este demo:
                Result.Success(Unit)
            } else {
                rollback(tempId)
                Result.Error("Error de conexión al enviar mensaje")
            }
        } catch (e: Exception) {
            rollback(tempId)
            Result.Error(e.message ?: "Error desconocido")
        }
    }

    private suspend fun rollback(id: String) {
        messageDao.deleteById(id)
    }
}
