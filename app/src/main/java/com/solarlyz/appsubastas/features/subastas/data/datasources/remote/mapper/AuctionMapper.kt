package com.solarlyz.appsubastas.features.subastas.data.datasources.remote.mapper

import com.solarlyz.appsubastas.features.subastas.data.models.AuctionDto
import com.solarlyz.appsubastas.features.subastas.domain.entities.Auction

fun AuctionDto.toDomain(): Auction {
    return Auction(
        id = this.id.toString(),
        title = this.title,
        description = this.description,
        imageUrl = this.imageUrl ?: "",
        currentPrice = this.currentPrice,
        bids = 0,
        timeRemaining = this.endDate,
        category = "General"
    )
}