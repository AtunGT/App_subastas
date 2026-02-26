package com.solarlyz.appsubastas.features.subastas.domain.repositories

import com.solarlyz.appsubastas.features.subastas.domain.entities.Auction


interface AuctionRepository {
        suspend fun getAuctions(): List<Auction>
}