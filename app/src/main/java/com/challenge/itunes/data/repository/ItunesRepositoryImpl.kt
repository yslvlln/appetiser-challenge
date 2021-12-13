package com.challenge.itunes.data.repository

import com.challenge.itunes.data.ItunesApiService
import com.challenge.itunes.data.model.MovieResponse
import com.haroldadmin.cnradapter.NetworkResponse

class ItunesRepositoryImpl(
    private val apiService: ItunesApiService
): ItunesRepository {

    override suspend fun getTrendingMovies(category: String): NetworkResponse<MovieResponse, Any> {
        return apiService.getTrendingMovies(category)
    }
}