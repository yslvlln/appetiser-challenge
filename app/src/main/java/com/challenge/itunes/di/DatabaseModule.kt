package com.challenge.itunes.di

import android.app.Application
import androidx.room.Room
import com.challenge.itunes.data.MovieDao
import com.challenge.itunes.data.MovieDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideMovieDao(database = get()) }
}
@Volatile
private var INSTANCE: MovieDataBase? = null

private fun provideDatabase(application: Application): MovieDataBase {
    val tempInstance = INSTANCE
    if (tempInstance != null) {
        return tempInstance
    }
    synchronized(application) {
        val instance = Room.databaseBuilder(
            application.applicationContext,
            MovieDataBase::class.java,
            "movie_database"
        ).build()
        INSTANCE = instance
        return instance
    }
}

private fun provideMovieDao(database: MovieDataBase): MovieDao {
    return  database.movieDao()
}