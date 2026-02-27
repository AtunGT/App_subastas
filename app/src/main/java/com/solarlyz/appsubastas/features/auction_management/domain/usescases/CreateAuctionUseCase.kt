package com.solarlyz.appsubastas.features.auction_management.domain.usecases

import com.solarlyz.appsubastas.features.auction_management.domain.repositories.AuctionManagementRepository
import javax.inject.Inject

class CreateAuctionUseCase @Inject constructor(
    private val repository: AuctionManagementRepository
) {
    suspend operator fun invoke(
        title: String,
        description: String,
        initialPrice: Double,
        startDate: String,
        endDate: String,
        userId: Int,
        categoryId: Int
    ): Result<Unit> {
        return repository.createAuction(
            title,
            description,
            initialPrice,
            startDate,
            endDate,
            userId,
            categoryId
        )
    }
}