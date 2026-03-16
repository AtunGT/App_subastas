package com.solarlyz.appsubastas.core.di

import com.solarlyz.appsubastas.features.auth.data.repository.AuthRepositoryImpl
import com.solarlyz.appsubastas.features.auth.domain.repository.AuthRepository
import com.solarlyz.appsubastas.features.chat.data.repository.MessageRepositoryImpl
import com.solarlyz.appsubastas.features.chat.domain.repository.MessageRepository
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
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindMessageRepository(messageRepositoryImpl: MessageRepositoryImpl): MessageRepository
}
