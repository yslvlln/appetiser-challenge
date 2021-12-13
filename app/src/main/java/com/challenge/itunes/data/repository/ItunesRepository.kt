package com.challenge.itunes.data.repository

import com.challenge.itunes.data.model.MovieResponse
import com.haroldadmin.cnradapter.NetworkResponse

interface ItunesRepository {
    suspend fun getTrendingMovies(category: String): NetworkResponse<MovieResponse, Any>
}