package com.solarlyz.appsubastas.features.auction_management.domain.usecases

import com.solarlyz.appsubastas.features.auction_management.domain.entities.AuctionDetail
import com.solarlyz.appsubastas.features.auction_management.domain.repositories.AuctionManagementRepository
import javax.inject.Inject

class GetAuctionDetailUseCase @Inject constructor(
    private val repository: AuctionManagementRepository
) {
    suspend operator fun invoke(id: Int): AuctionDetail {
        return repository.getAuctionById(id)
    }
}