package com.challenge.itunes.data

import androidx.room.TypeConverter
import com.challenge.itunes.data.model.RecoMoviesResponseResult
import com.challenge.itunes.data.model.TrendMoviesResponseResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converter {

    @TypeConverter
    fun fromMovieList(trendMovies: List<TrendMoviesResponseResult?>?): String? {
        if (trendMovies == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<TrendMoviesResponseResult?>?>() {}.type
        return gson.toJson(trendMovies, type)
    }

    @TypeConverter
    fun toMoviesList(moviesString: String?): List<TrendMoviesResponseResult>? {
        if (moviesString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<TrendMoviesResponseResult?>?>() {}.type
        return gson.fromJson<List<TrendMoviesResponseResult>>(moviesString, type)
    }

    @TypeConverter
    fun fromRecoMovieList(trendMovies: List<RecoMoviesResponseResult?>?): String? {
        if (trendMovies == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<RecoMoviesResponseResult?>?>() {}.type
        return gson.toJson(trendMovies, type)
    }

    @TypeConverter
    fun toRecoMoviesList(moviesString: String?): List<RecoMoviesResponseResult>? {
        if (moviesString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<RecoMoviesResponseResult?>?>() {}.type
        return gson.fromJson<List<RecoMoviesResponseResult>>(moviesString, type)
    }
}