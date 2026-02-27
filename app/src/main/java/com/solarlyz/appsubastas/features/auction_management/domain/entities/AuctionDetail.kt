package com.solarlyz.appsubastas.features.auction_management.domain.entities

data class AuctionDetail(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val initialPrice: Double,
    val currentPrice: Double,
    val startDate: String,
    val endDate: String,
    val status: String,
    val category: String,
    val sellerId: String
)