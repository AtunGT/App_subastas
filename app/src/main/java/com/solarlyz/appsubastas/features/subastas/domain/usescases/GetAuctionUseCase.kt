package com.solarlyz.appsubastas.features.subastas.domain.usecases

import com.solarlyz.appsubastas.features.subastas.domain.entities.Auction
import com.solarlyz.appsubastas.features.subastas.domain.repositories.AuctionRepository


class GetAuctionsUseCase(
    private val repository: AuctionRepository
) {
    suspend operator fun invoke(): List<Auction> {
        return repository.getAuctions()
    }
}