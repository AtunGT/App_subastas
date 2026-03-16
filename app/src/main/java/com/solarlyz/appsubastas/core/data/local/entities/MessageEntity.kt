package com.solarlyz.appsubastas.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey val id: String,
    val text: String,
    val timestamp: Long,
    val isOptimistic: Boolean
)
