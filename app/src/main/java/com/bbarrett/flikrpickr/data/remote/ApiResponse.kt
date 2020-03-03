package com.bbarrett.flikrpickr.data.remote

import java.lang.Exception

/**
 * @author bbarrett on 2020-02-25
 */

sealed class ApiResponse <out T: Any> {
    data class Success<out T: Any>(val data: T) : ApiResponse<T>()
    data class Error(val exception: Exception) : ApiResponse<Nothing>()
}
