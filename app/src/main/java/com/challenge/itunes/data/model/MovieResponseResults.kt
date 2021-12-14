package com.challenge.itunes.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Entity(tableName = "movie_table")
@Parcelize
data class MovieResponseResults(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @SerializedName("trackName") val trackName: String? = "",
    @SerializedName("artworkUrl30") val artworkUrl30: String? = "",
    @SerializedName("artworkUrl60") val artworkUrl60: String? = "",
    @SerializedName("artworkUrl100") val artworkUrl100: String? = "",
    @SerializedName("trackPrice") val trackPrice: Float? = 0f,
    @SerializedName("primaryGenreName") val primaryGenreName: String? = "",
    @SerializedName("shortDescription") val shortDesc: String? = "",
    @SerializedName("longDescription") val longDesc: String? = "",
    @SerializedName("trackViewUrl") val seeMore: String? = ""
): Parcelable