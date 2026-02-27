package com.solarlyz.appsubastas.features.auction_management.data.datasources.remote.api

import com.solarlyz.appsubastas.features.auction_management.data.models.AuctionDetailDto
import com.solarlyz.appsubastas.features.auction_management.data.models.BidRequest
import com.solarlyz.appsubastas.features.auction_management.data.models.CreateAuctionRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuctionManagementApi {

    @GET("auctions/{id}")
    suspend fun getAuctionById(
        @Path("id") id: Int
    ): Response<AuctionDetailDto>

    @POST("auctions")
    suspend fun createAuction(
        @Body request: CreateAuctionRequest
    ): Response<Unit>

    @POST("auctions/{id}/bid")
    suspend fun placeBid(
        @Path("id") id: Int,
        @Body request: BidRequest
    ): Response<Unit>
}