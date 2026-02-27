package com.solarlyz.appsubastas.features.subastas.data.repositories

import com.solarlyz.appsubastas.features.subastas.data.datasources.remote.api.AuctionApi
import com.solarlyz.appsubastas.features.subastas.data.datasources.remote.mapper.toDomain
import com.solarlyz.appsubastas.features.subastas.datasources.remote.models.AuctionDto
import com.solarlyz.appsubastas.features.subastas.domain.entities.Auction
import com.solarlyz.appsubastas.features.subastas.domain.repositories.AuctionRepository
import javax.inject.Inject

class AuctionRepositoryImpl @Inject constructor(
    private val api: AuctionApi
) : AuctionRepository {

    override suspend fun getAuctions(): List<Auction> {
        val dtoList = api.getAuctions()
        return dtoList.map { it.toDomain() }
    }
}