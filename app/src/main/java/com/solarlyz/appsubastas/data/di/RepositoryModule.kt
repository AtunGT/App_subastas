package com.solarlyz.appsubastas.data.di

import com.solarlyz.appsubastas.data.repositories.AuthRepositoryImpl
import com.solarlyz.appsubastas.domain.repositories.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}