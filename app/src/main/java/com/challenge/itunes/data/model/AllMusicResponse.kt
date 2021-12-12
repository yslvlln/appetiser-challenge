package com.challenge.itunes.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AllMusicResponse(
    @SerializedName("resultCount") val resultCount: Int? = 0,
    @SerializedName("results") val responseResultsAll: List<AllMusicResponseResults>? = listOf()
): Serializable