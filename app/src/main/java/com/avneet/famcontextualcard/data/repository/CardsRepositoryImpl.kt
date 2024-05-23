package com.avneet.famcontextualcard.data.repository

import com.avneet.famcontextualcard.data.db.hidden_card.HiddenCardDao
import com.avneet.famcontextualcard.data.models.CardGroupResponse
import com.avneet.famcontextualcard.data.models.HiddenCard
import com.avneet.famcontextualcard.data.networking.FamApiService
import com.avneet.famcontextualcard.domain.repository.CardsRepository
import com.avneet.famcontextualcard.data.utils.NetworkResult
import com.avneet.famcontextualcard.data.utils.toNetworkError
import com.avneet.famcontextualcard.data.utils.toNetworkResult
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CardsRepositoryImpl @Inject constructor(
    private val famApiService: FamApiService,
    private val hiddenCardDao: HiddenCardDao
) : CardsRepository {
    override fun fetchCards(): Flow<NetworkResult<CardGroupResponse>> = flow {
        val response = famApiService.fetchCards()
        val hiddenCardsIds = hiddenCardDao.getHiddenCardIds().first()
        emit(response.toNetworkResult { cardGroupList ->
            if (cardGroupList.isNullOrEmpty()) {
                NetworkResult.Error("No cards available")
            } else {
                val filteredCardGroups = cardGroupList[0].cardGroup?.map { cardGroup ->
                    cardGroup.copy(cardList = cardGroup.cardList.filterNot { card ->
                        hiddenCardsIds.contains(card.id)
                    })
                }
                NetworkResult.Success(CardGroupResponse(filteredCardGroups))
            }
        })
    }.catch { throwable ->
        emit(throwable.toNetworkError())
    }.flowOn(Dispatchers.IO)

    override suspend fun hideCard(cardId: Int) {
        hiddenCardDao.insert(HiddenCard(cardId))
    }
}
