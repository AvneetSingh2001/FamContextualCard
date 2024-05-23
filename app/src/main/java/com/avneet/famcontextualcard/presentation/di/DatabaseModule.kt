package com.avneet.famcontextualcard.presentation.di

import android.app.Application
import androidx.room.Room
import com.avneet.famcontextualcard.data.db.hidden_card.HiddenCardDao
import com.avneet.famcontextualcard.data.db.hidden_card.HiddenCardDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideHiddenCardDatabase(app: Application): HiddenCardDatabase {
        return Room.databaseBuilder(app, HiddenCardDatabase::class.java, "hidden_cards_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideHiddenCardDao(hiddenCardDatabase: HiddenCardDatabase): HiddenCardDao {
        return hiddenCardDatabase.hiddenCardDao()
    }

}