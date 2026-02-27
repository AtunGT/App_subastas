package com.solarlyz.appsubastas.features.subastas.data.di

import com.solarlyz.appsubastas.features.subastas.data.datasources.remote.api.AuctionApi
import com.solarlyz.appsubastas.features.subastas.data.repositories.AuctionRepositoryImpl
import com.solarlyz.appsubastas.features.subastas.domain.repositories.AuctionRepository
import com.solarlyz.appsubastas.features.subastas.domain.usecases.GetAuctionsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuctionModule {

    @Provides
    @Singleton
    fun provideAuctionApi(retrofit: Retrofit): AuctionApi {
        return retrofit.create(AuctionApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuctionRepository(
        api: AuctionApi
    ): AuctionRepository {
        return AuctionRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetAuctionsUseCase(
        repository: AuctionRepository
    ): GetAuctionsUseCase {
        return GetAuctionsUseCase(repository)
    }
}