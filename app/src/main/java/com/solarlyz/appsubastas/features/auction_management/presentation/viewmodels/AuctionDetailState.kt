package com.solarlyz.appsubastas.features.auction_management.presentation.viewmodels

import com.solarlyz.appsubastas.features.auction_management.domain.entities.AuctionDetail

sealed class AuctionDetailState {

    object Loading : AuctionDetailState()

    data class Success(
        val auction: AuctionDetail
    ) : AuctionDetailState()

    data class Error(
        val message: String
    ) : AuctionDetailState()
}