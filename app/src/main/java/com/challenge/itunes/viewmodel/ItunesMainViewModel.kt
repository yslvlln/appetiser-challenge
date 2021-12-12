package com.challenge.itunes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.itunes.data.model.AllMusicResponseResults
import com.challenge.itunes.data.repository.ItunesRepository
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItunesMainViewModel(private val repository: ItunesRepository): ViewModel() {

    private val _music = MutableLiveData<List<AllMusicResponseResults>?>()
    val music: LiveData<List<AllMusicResponseResults>?>
        get() = _music

    fun getAllMusic() {
        viewModelScope.launch {
            when(val result = withContext(Dispatchers.IO) { repository.getAllMusic() }) {
                is NetworkResponse.Success -> {
                    result.body.responseResultsAll?.let { _music.value = it }
                }
                else -> { _music.value = null }
            }
        }
    }
}