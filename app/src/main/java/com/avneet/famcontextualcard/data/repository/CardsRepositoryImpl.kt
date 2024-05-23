package com.avneet.famcontextualcard.data.repository

import com.avneet.famcontextualcard.data.models.CardGroupResponse
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
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CardsRepositoryImpl @Inject constructor(
    private val famApiService: FamApiService
) : CardsRepository {
    override fun fetchCards(): Flow<NetworkResult<CardGroupResponse>> = flow {
        val response = famApiService.fetchCards()
        emit(response.toNetworkResult { cardGroupList ->
            if (cardGroupList.isNullOrEmpty()) {
                NetworkResult.Error("No cards available")
            } else {
                NetworkResult.Success(cardGroupList[0])
            }
        })
    }.catch { throwable ->
        emit(throwable.toNetworkError())
    }.flowOn(Dispatchers.IO)
}
