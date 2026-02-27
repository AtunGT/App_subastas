package com.solarlyz.appsubastas.features.auction_management.data.models

import com.google.gson.annotations.SerializedName

data class AuctionDetailDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("initial_price")
    val initialPrice: Double,
    @SerializedName("current_price")
    val currentPrice: Double,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("category_id")
    val categoryId: Int,
    @SerializedName("user_id")
    val sellerId: String
)