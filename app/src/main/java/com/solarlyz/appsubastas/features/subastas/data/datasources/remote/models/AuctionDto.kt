package com.solarlyz.appsubastas.features.subastas.datasources.remote.models

data class AuctionDto(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val currentPrice: Double,
    val bids: Int,
    val timeRemaining: String,
    val category: String
)