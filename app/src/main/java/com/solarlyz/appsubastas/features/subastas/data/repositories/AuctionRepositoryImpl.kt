package com.solarlyz.appsubastas.features.subastas.data.repositories

import com.solarlyz.appsubastas.features.subastas.data.datasources.remote.mapper.toDomain
import com.solarlyz.appsubastas.features.subastas.datasources.remote.models.AuctionDto
import com.solarlyz.appsubastas.features.subastas.domain.repositories.AuctionRepository
import com.solarlyz.appsubastas.features.subastas.domain.entities.Auction
import javax.inject.Inject

class AuctionRepositoryImpl @Inject constructor() : AuctionRepository {

    override suspend fun getAuctions(): List<Auction> {
        val fakeData = listOf(
            AuctionDto(
                id = "1",
                title = "Reloj de Lujo Vintage",
                description = "Reloj automático suizo de edición limitada.",
                imageUrl = "https://picsum.photos/400",
                currentPrice = 7800.0,
                bids = 3,
                timeRemaining = "1h 36m",
                category = "Electrónica"
            ),
            AuctionDto(
                id = "2",
                title = "Cámara Clásica",
                description = "Cámara analógica en excelente estado.",
                imageUrl = "https://picsum.photos/401",
                currentPrice = 5400.0,
                bids = 5,
                timeRemaining = "4h 36m",
                category = "Arte"
            )
        )

        return fakeData.map { it.toDomain() }
    }
}