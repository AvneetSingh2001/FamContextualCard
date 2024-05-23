package com.avneet.famcontextualcard.domain.repository

import com.avneet.famcontextualcard.data.models.CardGroupResponse
import com.avneet.famcontextualcard.data.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface CardsRepository {
    fun fetchCards(): Flow<NetworkResult<CardGroupResponse>>
    suspend fun hideCard(cardId: Int)
}