package com.avneet.famcontextualcard.presentation.di

import com.avneet.famcontextualcard.data.networking.FamApiService
import com.avneet.famcontextualcard.data.repository.CardsRepositoryImpl
import com.avneet.famcontextualcard.domain.repository.CardsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideCardRepository(famApiService: FamApiService): CardsRepository {
        return CardsRepositoryImpl(
            famApiService = famApiService
        )
    }
}