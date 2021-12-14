package com.challenge.itunes.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TrendMoviesResponse(
    @SerializedName("resultCount") val resultCount: Int? = 0,
    @SerializedName("results") val responseResultAll: List<TrendMoviesResponseResult>? = listOf()
): Serializable