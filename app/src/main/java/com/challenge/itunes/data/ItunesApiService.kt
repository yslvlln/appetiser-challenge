package com.challenge.itunes.data

import com.challenge.itunes.data.model.MovieResponse
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesApiService {

    @GET("/search?")
    suspend fun getTrendingMovies(
        @Query("term") term: String = "star",
        @Query("country") country: String = "au",
        @Query("media") media: String = "movie",
        @Query("attribute") attr: String = "ratingTerm",
        @Query("limit") limit: Int = 3
    ): NetworkResponse<MovieResponse, String>

}