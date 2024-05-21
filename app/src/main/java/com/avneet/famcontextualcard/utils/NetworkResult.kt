package com.avneet.famcontextualcard.utils

sealed class NetworkResult<out T> {
    class Loading(val message: String?) : NetworkResult<Nothing>()
    class Success<out T>(val data: T?) : NetworkResult<T>()
    class Error(val exception: Throwable?) : NetworkResult<Nothing>()
}