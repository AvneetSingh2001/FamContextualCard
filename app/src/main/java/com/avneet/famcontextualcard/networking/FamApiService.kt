package com.avneet.famcontextualcard.networking

import com.avneet.famcontextualcard.models.CardGroupResponse
import com.avneet.famcontextualcard.networking.Endpoints.CARD_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface FamApiService {
    @GET(CARD_ENDPOINT)
    suspend fun fetchCards(): Response<CardGroupResponse>
}