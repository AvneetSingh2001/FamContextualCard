package com.avneet.famcontextualcard.data.utils

import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

fun <T> Response<List<T>>.toNetworkResult(
    onSuccess: (List<T>) -> NetworkResult<T>
): NetworkResult<T> {
    return if (isSuccessful) {
        body()?.let {
            onSuccess(it)
        } ?: NetworkResult.Error("Response body is null")
    } else {
        NetworkResult.Error(message())
    }
}

fun Throwable.toNetworkError(): NetworkResult.Error {
    val errorMessage = when (this) {
        is IOException -> "Network error, please check your connection."
        is HttpException -> {
            val code = code()
            val errorResponse = response()?.errorBody()?.string().orEmpty()
            "HTTP error $code: $errorResponse"
        }

        is JsonParseException, is JsonSyntaxException -> "Parsing error, please try again."
        else -> "Unknown error occurred: ${message.orEmpty()}"
    }
    return NetworkResult.Error(errorMessage)
}