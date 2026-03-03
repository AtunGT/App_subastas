package com.solarlyz.appsubastas.features.auction_management.data.datasources.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class BidUpdate(
    val auction_id: Int,
    val new_price: Double
)