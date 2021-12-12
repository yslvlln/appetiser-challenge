package com.challenge.itunes.data.repository

import com.challenge.itunes.data.ItunesApiService
import com.challenge.itunes.data.model.AllMusicResponse
import com.haroldadmin.cnradapter.NetworkResponse

class ItunesRepositoryImpl(
    private val apiService: ItunesApiService
): ItunesRepository {

    override suspend fun getAllMusic(): NetworkResponse<AllMusicResponse, Any> {
        return apiService.getAllMusic()
    }
}