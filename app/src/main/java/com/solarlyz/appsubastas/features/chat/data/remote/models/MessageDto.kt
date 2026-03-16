package com.solarlyz.appsubastas.features.chat.data.remote.models

data class MessageDto(
    val id: String,
    val text: String,
    val timestamp: Long,
    val senderId: Int
)
