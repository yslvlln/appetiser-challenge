package com.challenge.itunes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.itunes.data.model.MovieResponseResults
import com.challenge.itunes.data.repository.ItunesRepository
import com.challenge.itunes.utilities.SingleLiveEvent
import com.challenge.itunes.utilities.constant.DEFAULT_TAG
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ItunesMainViewModel(private val repository: ItunesRepository) : ViewModel() {

    private val _trendMovies = MutableLiveData<List<MovieResponseResults>>()
    val trendMovies: LiveData<List<MovieResponseResults>>
        get() = _trendMovies
    val isTrendLoading = SingleLiveEvent<Boolean>()

    fun getTrendingMovies(category: String) {
        viewModelScope.launch {
            isTrendLoading.value = true
            when (val result = withContext(Dispatchers.IO) { repository.getTrendingMovies(category.toLowerCase(Locale.getDefault())) }) {
                is NetworkResponse.Success -> {
                    Log.d(DEFAULT_TAG, "from network")
                    result.body.responseResultsAll?.let {
                        _trendMovies.value = it
                    }
                }
                else -> {
                    Log.d(DEFAULT_TAG, "from cache")
                    _trendMovies.value = repository.getTrendMoviesCache(category)
                }
            }
        }.invokeOnCompletion {
            isTrendLoading.value = false
        }
    }
}