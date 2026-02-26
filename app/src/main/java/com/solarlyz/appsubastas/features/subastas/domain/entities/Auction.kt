package com.solarlyz.appsubastas.features.subastas.domain.entities

data class Auction(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val currentPrice: Double,
    val bids: Int,
    val timeRemaining: String,
    val category: String
)