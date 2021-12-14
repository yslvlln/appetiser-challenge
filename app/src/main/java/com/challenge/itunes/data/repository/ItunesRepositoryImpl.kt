package com.challenge.itunes.data.repository

import androidx.lifecycle.LiveData
import com.challenge.itunes.data.ItunesApiService
import com.challenge.itunes.data.MovieDao
import com.challenge.itunes.data.model.RecoMoviesResponse
import com.challenge.itunes.data.model.RecoMoviesResponseResult
import com.challenge.itunes.data.model.TrendMoviesResponse
import com.challenge.itunes.data.model.TrendMoviesResponseResult
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

    override suspend fun getTrendMoviesCache(category: String): List<TrendMoviesResponseResult> {
        return movieDao.getByCategory("%$category%")
    }

    override suspend fun getTrendingMovies(category: String): NetworkResponse<TrendMoviesResponse, String> {
        return when(val result = withContext(Dispatchers.IO) { apiService.getTrendingMovies(term = category) }) {
            is NetworkResponse.Success -> {
                //Insert the trending movies in the database
                //every successful API call for persistence.
                //This makes the app usable even without data later on.
                result.body.responseResultAll?.let {
                    movieDao.insertAllTrend(it)
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

    override suspend fun getRecommendedCache(): List<RecoMoviesResponseResult> {
        return movieDao.getAlLReco()
    }

    override suspend fun getRecommendedMovies(): NetworkResponse<RecoMoviesResponse, String> {
        return when(val result = withContext(Dispatchers.IO) { apiService.getRecommendedMovies() }) {
            is NetworkResponse.Success -> {
                //Insert the trending movies in the database
                //every successful API call for persistence.
                //This makes the app usable even without data later on.
                result.body.responseResultAll?.let {
                    movieDao.insertAllReco(it)
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

    override fun searchRecoMovies(searchString: String): LiveData<List<RecoMoviesResponseResult>> {
        return movieDao.searchDatabase(searchString)
    }
}