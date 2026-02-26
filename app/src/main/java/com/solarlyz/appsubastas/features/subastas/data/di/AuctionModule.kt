package com.solarlyz.appsubastas.features.subastas.data.di

import com.solarlyz.appsubastas.features.subastas.data.repositories.AuctionRepositoryImpl
import com.solarlyz.appsubastas.features.subastas.domain.repositories.AuctionRepository
import com.solarlyz.appsubastas.features.subastas.domain.usecases.GetAuctionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuctionModule {

    @Provides
    @Singleton
    fun provideAuctionRepository(): AuctionRepository {
        return AuctionRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideGetAuctionsUseCase(
        repository: AuctionRepository
    ): GetAuctionsUseCase {
        return GetAuctionsUseCase(repository)
    }
}