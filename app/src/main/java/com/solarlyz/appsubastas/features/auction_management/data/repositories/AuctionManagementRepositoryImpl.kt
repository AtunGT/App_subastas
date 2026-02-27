package com.solarlyz.appsubastas.features.auction_management.data.repositories

import com.solarlyz.appsubastas.features.auction_management.data.datasources.remote.api.AuctionManagementApi
import com.solarlyz.appsubastas.features.auction_management.data.datasources.remote.mapper.toDomain
import com.solarlyz.appsubastas.features.auction_management.data.models.BidRequest
import com.solarlyz.appsubastas.features.auction_management.data.models.CreateAuctionRequest
import com.solarlyz.appsubastas.features.auction_management.domain.entities.AuctionDetail
import com.solarlyz.appsubastas.features.auction_management.domain.repositories.AuctionManagementRepository
import javax.inject.Inject

class AuctionManagementRepositoryImpl @Inject constructor(
    private val api: AuctionManagementApi
) : AuctionManagementRepository {

    override suspend fun getAuctionById(id: Int): AuctionDetail {
        val response = api.getAuctionById(id)

        if (response.isSuccessful && response.body() != null) {
            return response.body()!!.toDomain()
        } else {
            throw Exception("Error al obtener detalle")
        }
    }

    override suspend fun createAuction(
        title: String,
        description: String,
        initialPrice: Double,
        startDate: String,
        endDate: String,
        userId: Int,
        categoryId: Int
    ): Result<Unit> {
        return try {
            val request = CreateAuctionRequest(
                title = title,
                description = description,
                initialPrice = initialPrice,
                startDate = startDate,
                endDate = endDate,
                userId = userId,
                categoryId = categoryId
            )

            val response = api.createAuction(request)

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error ${response.code()}"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun placeBid(
        auctionId: Int,
        amount: Double,
        userId: Int
    ): Result<Unit> {
        return try {

            val response = api.placeBid(
                auctionId,
                BidRequest(amount = amount, user_id = userId)
            )

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Error ${response.code()}"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}