package com.challenge.itunes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.itunes.data.model.RecoMoviesResponseResult
import com.challenge.itunes.data.model.TrendMoviesResponseResult
import com.challenge.itunes.data.repository.ItunesRepository
import com.challenge.itunes.utilities.SingleLiveEvent
import com.challenge.itunes.utilities.constant.DEFAULT_TAG
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ItunesMainViewModel(private val repository: ItunesRepository) : ViewModel() {

    private val _trendMovies = MutableLiveData<List<TrendMoviesResponseResult>>()
    val trendMovies: LiveData<List<TrendMoviesResponseResult>>
        get() = _trendMovies

    private val _recoMovies = MutableLiveData<List<RecoMoviesResponseResult>>()
    val recoMovies: LiveData<List<RecoMoviesResponseResult>>
        get() = _recoMovies

    private var currentCategory = ""
    val isTrendLoading = SingleLiveEvent<Boolean>()

    fun getTrendingMovies(category: String) {
        //Do not reload if same category
        if (currentCategory == category) {
            return
        }
        currentCategory = category
        viewModelScope.launch {
            isTrendLoading.value = true
            when (val result = withContext(Dispatchers.IO) { repository.getTrendingMovies(category.toLowerCase(Locale.getDefault())) }) {
                is NetworkResponse.Success -> {
                    //Update the observer data from API response
                    Log.d(DEFAULT_TAG, "trend from network")
                    result.body.responseResultAll?.let {
                        _trendMovies.value = it
                    }
                }
                else -> {
                    //If the user does not have any internet connection
                    //it will fall under this condition and update the same observer
                    //using the data from room db
                    Log.d(DEFAULT_TAG, "trend from cache")
                    _trendMovies.value = repository.getTrendMoviesCache(category)
                }
            }
        }.invokeOnCompletion {
            isTrendLoading.value = false
        }
    }

    fun getRecommendedMovies() {
        viewModelScope.launch {
            isTrendLoading.value = true
            when (val result = withContext(Dispatchers.IO) { repository.getRecommendedMovies() }) {
                is NetworkResponse.Success -> {
                    //Update the observer data from API response
                    Log.d(DEFAULT_TAG, "reco from network")
                    result.body.responseResultAll?.let {
                        _recoMovies.value = it
                    }
                }
                else -> {
                    //If the user does not have any internet connection
                    //it will fall under this condition and update the same observer
                    //using the data from room db
                    Log.d(DEFAULT_TAG, "reco from cache")
                    _recoMovies.value = repository.getRecommendedCache()
                }
            }
        }.invokeOnCompletion {
            isTrendLoading.value = false
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<RecoMoviesResponseResult>> {
        return repository.searchRecoMovies(searchQuery)
    }
}