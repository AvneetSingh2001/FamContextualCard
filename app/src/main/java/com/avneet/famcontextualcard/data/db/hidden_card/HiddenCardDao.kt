package com.avneet.famcontextualcard.data.db.hidden_card

import com.avneet.famcontextualcard.data.models.HiddenCard
import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HiddenCardDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(hiddenCard: HiddenCard)

    @Query("SELECT cardId FROM hidden_cards_db")
    fun getHiddenCardIds(): Flow<List<Int>>

    @Query("DELETE FROM hidden_cards_db WHERE cardId = :cardId")
    suspend fun delete(cardId: Int)
}