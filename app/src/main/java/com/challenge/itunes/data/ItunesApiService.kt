package com.challenge.itunes.data

import com.challenge.itunes.data.model.RecoMoviesResponse
import com.challenge.itunes.data.model.TrendMoviesResponse
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryName

interface ItunesApiService {

    @GET("/search?")
    suspend fun getTrendingMovies(
        @Query("term") term: String,
        @Query("country") country: String = "au",
        @Query("media") media: String = "movie",
        @Query("attribute") attr: String = "ratingTerm",
        @Query("limit") limit: Int = 3
    ): NetworkResponse<TrendMoviesResponse, String>

    @GET("/search?")
    suspend fun getRecommendedMovies(
        @Query("term") term: String = "star",
        @Query("country") country: String = "au",
        @Query("media") media: String = "movie",
        @QueryName filter: String = "all"
    ): NetworkResponse<RecoMoviesResponse, String>

}