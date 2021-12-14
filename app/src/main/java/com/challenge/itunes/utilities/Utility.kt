package com.challenge.itunes.utilities

import com.challenge.itunes.utilities.constant.colors
import com.haroldadmin.cnradapter.NetworkResponse

fun randomizeColor(): Int {
    return colors.random()
}

fun <T : Any> handleServerError(resp: NetworkResponse.ServerError<T>): NetworkResponse.ServerError<T>  {
    return NetworkResponse.ServerError(resp.body, resp.code, resp.headers)
}

fun <T : Any> handleApiSuccess(response: NetworkResponse.Success<T>): NetworkResponse.Success<T> {
    return NetworkResponse.Success(response.body, response.headers, response.code)
}

fun handleNetworkError(resp: NetworkResponse.NetworkError): NetworkResponse.NetworkError  {
    return NetworkResponse.NetworkError(resp.error)
}

fun handleUnknownError(response: NetworkResponse.UnknownError): NetworkResponse.UnknownError {
    return NetworkResponse.UnknownError(response.error, response.code, response.headers)
}