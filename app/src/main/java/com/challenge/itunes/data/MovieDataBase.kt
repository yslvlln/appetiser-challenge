package com.challenge.itunes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.challenge.itunes.data.model.Converter
import com.challenge.itunes.data.model.MovieResponseResults

@Database(entities = [MovieResponseResults::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class MovieDataBase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}