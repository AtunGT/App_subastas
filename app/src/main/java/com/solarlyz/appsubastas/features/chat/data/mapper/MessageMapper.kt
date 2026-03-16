package com.solarlyz.appsubastas.features.chat.data.mapper

import com.solarlyz.appsubastas.core.data.local.entities.MessageEntity
import com.solarlyz.appsubastas.features.chat.domain.model.Message

fun MessageEntity.toDomain(): Message {
    return Message(
        id = id,
        text = text,
        timestamp = timestamp,
        isSent = !isOptimistic
    )
}

fun Message.toEntity(isOptimistic: Boolean = false): MessageEntity {
    return MessageEntity(
        id = id,
        text = text,
        timestamp = timestamp,
        isOptimistic = isOptimistic
    )
}
