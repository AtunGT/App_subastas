package com.solarlyz.appsubastas.features.subastas.data.datasources.remote.mapper

import com.solarlyz.appsubastas.features.subastas.datasources.remote.models.AuctionDto
import com.solarlyz.appsubastas.features.subastas.domain.entities.Auction

fun AuctionDto.toDomain(): Auction {
    return Auction(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        currentPrice = currentPrice,
        bids = bids,
        timeRemaining = timeRemaining,
        category = category
    )
}