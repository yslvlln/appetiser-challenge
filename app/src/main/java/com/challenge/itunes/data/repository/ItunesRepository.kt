package com.challenge.itunes.data.repository

import com.challenge.itunes.data.model.AllMusicResponse
import com.haroldadmin.cnradapter.NetworkResponse

interface ItunesRepository {
    suspend fun getAllMusic(): NetworkResponse<AllMusicResponse, Any>
}