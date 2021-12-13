package com.challenge.itunes.di

import com.challenge.itunes.BuildConfig
import com.challenge.itunes.data.ItunesApiService
import com.challenge.itunes.utilities.constant.REQUEST_TIME_OUT
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(okHttpClient = get()) }
    single { provideApiService(retrofit = get()) }
}

private fun provideRetrofit(
    okHttpClient: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .client(okHttpClient)
        .build()
}

private fun provideOkHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder()
        .connectTimeout(REQUEST_TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(REQUEST_TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(REQUEST_TIME_OUT, TimeUnit.SECONDS)
    return client.addInterceptor {
        val original = it.request()
        val requestBuilder = original.newBuilder()
        requestBuilder.addHeader("Connection", "close")
        requestBuilder.addHeader("Accept", "application/json")
        requestBuilder.addHeader("Content-Type", "application/json")
        val request = requestBuilder.method(original.method(), original.body()).build()
        return@addInterceptor it.proceed(request)
    }.addNetworkInterceptor(StethoInterceptor()).build()
}

private fun provideApiService(retrofit: Retrofit): ItunesApiService = retrofit.create(ItunesApiService::class.java)