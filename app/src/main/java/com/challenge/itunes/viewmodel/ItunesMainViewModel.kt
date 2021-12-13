package com.challenge.itunes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.itunes.data.model.MovieResponseResults
import com.challenge.itunes.data.repository.ItunesRepository
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ItunesMainViewModel(private val repository: ItunesRepository): ViewModel() {

    private val _trendingMovies = MutableLiveData<List<MovieResponseResults>?>()
    val trendingMovies: LiveData<List<MovieResponseResults>?>
        get() = _trendingMovies

    private val _isTrendLoading = MutableLiveData<Boolean>()
    val isTrendLoading: LiveData<Boolean>
        get() = _isTrendLoading

    fun getTrendingMovies(category: String) {
        viewModelScope.launch {
            _isTrendLoading.value = true
            when(val result = withContext(Dispatchers.IO) {
                repository.getTrendingMovies(category.lowercase(Locale.getDefault()))
            }) {
                is NetworkResponse.Success -> {
                    result.body.responseResultsAll?.let { _trendingMovies.value = it }
                }
                else -> { _trendingMovies.value = null }
            }
        }.invokeOnCompletion {
            _isTrendLoading.value = false
        }
    }
}