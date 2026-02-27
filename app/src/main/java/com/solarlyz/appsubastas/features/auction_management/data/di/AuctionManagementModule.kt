package com.solarlyz.appsubastas.features.auction_management.data.di

import com.solarlyz.appsubastas.features.auction_management.data.datasources.remote.api.AuctionManagementApi
import com.solarlyz.appsubastas.features.auction_management.data.repositories.AuctionManagementRepositoryImpl
import com.solarlyz.appsubastas.features.auction_management.domain.repositories.AuctionManagementRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuctionManagementModule {

    @Provides
    @Singleton
    fun provideAuctionManagementApi(
        retrofit: Retrofit
    ): AuctionManagementApi {
        return retrofit.create(AuctionManagementApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuctionManagementRepository(
        api: AuctionManagementApi
    ): AuctionManagementRepository {
        return AuctionManagementRepositoryImpl(api)
    }
}