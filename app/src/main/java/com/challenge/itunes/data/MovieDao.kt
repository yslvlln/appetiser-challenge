package com.challenge.itunes.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.challenge.itunes.data.model.MovieResponseResults

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieResponseResults>)

    @Query("SELECT * FROM movie_table WHERE primaryGenreName LIKE :category LIMIT 3")
    suspend fun getByCategory(category: String): List<MovieResponseResults>

}