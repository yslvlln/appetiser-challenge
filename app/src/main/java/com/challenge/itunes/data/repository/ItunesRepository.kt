package com.challenge.itunes.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.challenge.itunes.data.model.MovieResponse
import com.challenge.itunes.data.model.MovieResponseResults
import com.haroldadmin.cnradapter.NetworkResponse

interface ItunesRepository {
    suspend fun getTrendMoviesCache(category: String): List<MovieResponseResults>
    suspend fun getTrendingMovies(category: String): NetworkResponse<MovieResponse, String>
}