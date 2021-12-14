package com.challenge.itunes.utilities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.challenge.itunes.utilities.constant.colors
import com.haroldadmin.cnradapter.NetworkResponse
import java.lang.ref.WeakReference

fun randomizeColor(): Int {
    return colors.random()
}

fun openInBrowser(context: Context, url: String) {
    val mUrl = url.replace("https://", "http://")
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mUrl))
    ContextCompat.startActivity(context, browserIntent, Bundle())
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