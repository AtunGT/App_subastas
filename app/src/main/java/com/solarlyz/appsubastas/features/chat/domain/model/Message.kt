package com.solarlyz.appsubastas.features.chat.domain.model

data class Message(
    val id: String,
    val text: String,
    val timestamp: Long,
    val isSent: Boolean = true
)
