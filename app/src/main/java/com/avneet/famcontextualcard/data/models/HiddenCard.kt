package com.avneet.famcontextualcard.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hidden_cards_db")
data class HiddenCard(
    @PrimaryKey val cardId: Int
)