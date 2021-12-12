package com.challenge.itunes.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AllMusicResponseResults(
    @SerializedName("trackName") val trackName: String? = "",
    @SerializedName("artworkUrl30") val artworkUrl30: String? = "",
    @SerializedName("artworkUrl60") val artworkUrl60: String? = "",
    @SerializedName("artworkUrl100") val artworkUrl100: String? = "",
    @SerializedName("trackPrice") val trackPrice: Float? = 0f,
    @SerializedName("primaryGenreName") val primaryGenreName: String? = ""
): Serializable