package com.solarlyz.appsubastas.features.subastas.data.datasources.remote.mapper

import com.solarlyz.appsubastas.features.subastas.datasources.remote.models.AuctionDto
import com.solarlyz.appsubastas.features.subastas.domain.entities.Auction

fun AuctionDto.toDomain(): Auction {
    return Auction(
        id = this.id.toString(),
        title = this.title,
        description = this.description,
        imageUrl = "https://picsum.photos/seed/${this.id}/400/400",
        currentPrice = this.currentPrice,
        bids = 0,
        timeRemaining = "Termina: ${this.endDate.substringBefore("T")}",
        category = when (this.categoryId) {
            1 -> "Electrónica"
            2 -> "Arte"
            3 -> "Moda"
            else -> "Otros"
        }
    )
}