package com.challenge.itunes.data.repository

import com.challenge.itunes.data.ItunesApiService
import com.challenge.itunes.data.MovieDao
import com.challenge.itunes.data.model.MovieResponse
import com.challenge.itunes.data.model.MovieResponseResults
import com.challenge.itunes.utilities.handleApiSuccess
import com.challenge.itunes.utilities.handleNetworkError
import com.challenge.itunes.utilities.handleServerError
import com.challenge.itunes.utilities.handleUnknownError
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItunesRepositoryImpl(
    private val apiService: ItunesApiService,
    private val movieDao: MovieDao
): ItunesRepository {

    override suspend fun getTrendMoviesCache(category: String): List<MovieResponseResults> {
        return movieDao.getByCategory("%$category%")
    }

    override suspend fun getTrendingMovies(category: String): NetworkResponse<MovieResponse, String> {
        return when(val result = withContext(Dispatchers.IO) { apiService.getTrendingMovies(category) }) {
            is NetworkResponse.Success -> {
                result.body.responseResultsAll?.let {
                    movieDao.insertAll(it)
                }
                handleApiSuccess(result)
            }
            is NetworkResponse.ServerError -> {
                handleServerError(result)
            }
            is NetworkResponse.NetworkError -> {
                handleNetworkError(result)
            }
            is NetworkResponse.UnknownError -> {
                handleUnknownError(result)
            }
        }
    }
}