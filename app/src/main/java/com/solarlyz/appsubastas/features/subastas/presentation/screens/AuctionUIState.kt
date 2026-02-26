package com.solarlyz.appsubastas.features.subastas.presentation.screens

import com.solarlyz.appsubastas.features.subastas.domain.entities.Auction


data class AuctionUIState(
    val auctions: List<Auction> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)