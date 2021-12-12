package com.challenge.itunes.data

import com.challenge.itunes.data.model.AllMusicResponse
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryName

interface ItunesApiService {

    @GET("/search?")
    suspend fun getAllMusic(
        @Query("term") term: String = "star",
        @Query("country") country: String = "au",
        @Query("media") media: String = "movie",
        @QueryName query: String = "all"
    ): NetworkResponse<AllMusicResponse, Any>

}