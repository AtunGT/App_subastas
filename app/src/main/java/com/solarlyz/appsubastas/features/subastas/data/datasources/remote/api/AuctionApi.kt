package com.solarlyz.appsubastas.features.subastas.data.datasources.remote.api


import com.solarlyz.appsubastas.features.subastas.datasources.remote.models.AuctionDto
import retrofit2.http.GET

interface AuctionApi {
    @GET("auctions/")
    suspend fun getAuctions(): List<AuctionDto>
}