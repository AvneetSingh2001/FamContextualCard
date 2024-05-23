package com.avneet.famcontextualcard.data.db.hidden_card


import androidx.room.Database
import androidx.room.RoomDatabase
import com.avneet.famcontextualcard.data.models.HiddenCard

@Database(entities = [HiddenCard::class], version = 1)
abstract class HiddenCardDatabase : RoomDatabase() {
    abstract fun hiddenCardDao(): HiddenCardDao
}