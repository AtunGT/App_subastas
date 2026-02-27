package com.solarlyz.appsubastas.features.auction_management.domain.repositories

import com.solarlyz.appsubastas.features.auction_management.domain.entities.AuctionDetail

interface AuctionManagementRepository {

        suspend fun getAuctionById(id: Int): AuctionDetail

        suspend fun createAuction(
                title: String,
                description: String,
                initialPrice: Double,
                startDate: String,
                endDate: String,
                userId: Int,
                categoryId: Int
        ): Result<Unit>

        suspend fun placeBid(
                auctionId: Int,
                amount: Double,
                userId: Int
        ): Result<Unit>
}