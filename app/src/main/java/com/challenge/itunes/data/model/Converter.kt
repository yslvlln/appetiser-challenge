package com.challenge.itunes.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converter {

    @TypeConverter
    fun fromMovieList(movies: List<MovieResponseResults?>?): String? {
        if (movies == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<MovieResponseResults?>?>() {}.type
        return gson.toJson(movies, type)
    }

    @TypeConverter
    fun toMoviesList(moviesString: String?): List<MovieResponseResults>? {
        if (moviesString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<MovieResponseResults?>?>() {}.type
        return gson.fromJson<List<MovieResponseResults>>(moviesString, type)
    }
}