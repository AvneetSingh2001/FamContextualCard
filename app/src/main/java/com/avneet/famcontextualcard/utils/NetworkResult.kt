package com.avneet.famcontextualcard.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class NetworkResult<out T> {
    data object Loading : NetworkResult<Nothing>()
    class Success<out T>(val data: T?) : NetworkResult<T>()
    class Error(var error: String?) : NetworkResult<Nothing>()
}