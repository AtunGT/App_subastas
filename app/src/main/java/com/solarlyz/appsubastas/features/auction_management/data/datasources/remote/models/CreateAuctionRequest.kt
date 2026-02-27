package com.solarlyz.appsubastas.features.auction_management.data.models

import com.google.gson.annotations.SerializedName

data class CreateAuctionRequest(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("initial_price") val initialPrice: Double,
    @SerializedName("start_date") val startDate: String,
    @SerializedName("end_date") val endDate: String,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("category_id") val categoryId: Int
)