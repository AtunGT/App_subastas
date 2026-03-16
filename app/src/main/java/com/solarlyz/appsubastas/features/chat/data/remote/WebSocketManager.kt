package com.solarlyz.appsubastas.features.chat.data.remote

import com.solarlyz.appsubastas.core.data.local.dao.MessageDao
import com.solarlyz.appsubastas.core.data.local.entities.MessageEntity
import com.solarlyz.appsubastas.features.chat.data.remote.models.MessageDto
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebSocketManager @Inject constructor(
    private val client: OkHttpClient,
    private val messageDao: MessageDao,
    private val gson: Gson
) {
    private var webSocket: WebSocket? = null
    private val scope = CoroutineScope(Dispatchers.IO)

    fun connect(url: String) {
        val request = Request.Builder().url(url).build()
        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onMessage(webSocket: WebSocket, text: String) {
                val messageDto = gson.fromJson(text, MessageDto::class.java)
                scope.launch {
                    messageDao.insert(
                        MessageEntity(
                            id = messageDto.id,
                            text = messageDto.text,
                            timestamp = messageDto.timestamp,
                            isOptimistic = false
                        )
                    )
                }
            }
        })
    }

    fun sendMessage(text: String): Boolean {
        return webSocket?.send(text) ?: false
    }

    fun disconnect() {
        webSocket?.close(1000, "Cerrado por el usuario")
    }
}
