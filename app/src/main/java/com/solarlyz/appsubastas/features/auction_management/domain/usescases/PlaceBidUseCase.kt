package com.solarlyz.appsubastas.features.auction_management.domain.usecases

import com.solarlyz.appsubastas.features.auction_management.domain.repositories.AuctionManagementRepository
import javax.inject.Inject

class PlaceBidUseCase @Inject constructor(
    private val repository: AuctionManagementRepository
) {
    suspend operator fun invoke(
        auctionId: Int,
        amount: Double,
        userId: Int
    ): Result<Unit> {
        return repository.placeBid(auctionId, amount, userId)
    }
}