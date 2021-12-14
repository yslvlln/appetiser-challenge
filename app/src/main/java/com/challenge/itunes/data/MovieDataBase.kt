package com.challenge.itunes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.challenge.itunes.data.model.RecoMoviesResponseResult
import com.challenge.itunes.data.model.TrendMoviesResponseResult

@Database(
    entities = [
        TrendMoviesResponseResult::class, RecoMoviesResponseResult::class
    ], version = 4, exportSchema = false
)
@TypeConverters(Converter::class)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}