package com.avneet.famcontextualcard.data.repository

import com.avneet.famcontextualcard.data.models.CardGroupResponse
import com.avneet.famcontextualcard.data.networking.FamApiService
import com.avneet.famcontextualcard.domain.repository.CardsRepository
import com.avneet.famcontextualcard.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class CardsRepositoryImpl @Inject constructor(
    private val famApiService: FamApiService
): CardsRepository {
    override fun fetchCards(): Flow<NetworkResult<CardGroupResponse>> = flow {
        emit(responseToNetworkResult(famApiService.fetchCards()))
    }.catch {
        emit(NetworkResult.Error(it.message))
    }.flowOn(Dispatchers.IO)

    private fun<T> responseToNetworkResult(response: Response<T>): NetworkResult<T> {
        if (response.isSuccessful) {
            response.body()?.let {
                return NetworkResult.Success(it)
            }
        }
        return NetworkResult.Error(response.message())
    }
}