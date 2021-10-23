package com.ysanjeet535.newsbox.utils

sealed class ResponseHandler<T>(val data:T? = null, val message:String? = null){
    class Success<T>(data: T) : ResponseHandler<T>(data = data)
    class Error<T>(message: String?) : ResponseHandler<T>(message = message)
    class Loading<T>(data: T? = null) : ResponseHandler<T>(data)
}
