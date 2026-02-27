package com.solarlyz.appsubastas.features.subastas.datasources.remote.models

import com.google.gson.annotations.SerializedName

data class AuctionDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
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
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("category_id")
    val categoryId: Int
)