package com.solarlyz.appsubastas.features.auction_management.data.datasources.remote.mapper

import com.solarlyz.appsubastas.features.auction_management.data.models.AuctionDetailDto
import com.solarlyz.appsubastas.features.auction_management.domain.entities.AuctionDetail

fun AuctionDetailDto.toDomain(): AuctionDetail {
    return AuctionDetail(
        id = this.id,
        title = this.title,
        description = this.description,
        imageUrl = this.imageUrl ?: "",
        initialPrice = this.initialPrice,
        currentPrice = this.currentPrice,
        startDate = this.startDate,
        endDate = this.endDate,
        status = this.status,
        category = this.categoryId.toString(),
        sellerId = this.sellerId
    )
}