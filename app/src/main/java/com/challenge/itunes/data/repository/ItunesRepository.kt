package com.challenge.itunes.data.repository

import androidx.lifecycle.LiveData
import com.challenge.itunes.data.model.RecoMoviesResponse
import com.challenge.itunes.data.model.RecoMoviesResponseResult
import com.challenge.itunes.data.model.TrendMoviesResponse
import com.challenge.itunes.data.model.TrendMoviesResponseResult
import com.haroldadmin.cnradapter.NetworkResponse

interface ItunesRepository {
    suspend fun getTrendMoviesCache(category: String): List<TrendMoviesResponseResult>
    suspend fun getTrendingMovies(category: String): NetworkResponse<TrendMoviesResponse, String>
    suspend fun getRecommendedCache(): List<RecoMoviesResponseResult>
    suspend fun getRecommendedMovies(): NetworkResponse<RecoMoviesResponse, String>
    fun searchRecoMovies(searchString: String): LiveData<List<RecoMoviesResponseResult>>
}