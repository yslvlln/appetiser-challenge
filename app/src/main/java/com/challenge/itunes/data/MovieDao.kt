package com.challenge.itunes.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.challenge.itunes.data.model.RecoMoviesResponseResult
import com.challenge.itunes.data.model.TrendMoviesResponseResult

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTrend(trendMovies: List<TrendMoviesResponseResult>)

    @Query("SELECT * FROM trend_table WHERE primaryGenreName LIKE :category LIMIT 3")
    suspend fun getByCategory(category: String): List<TrendMoviesResponseResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllReco(trendMovies: List<RecoMoviesResponseResult>)

    @Query("SELECT * FROM reco_table")
    suspend fun getAlLReco(): List<RecoMoviesResponseResult>

    @Query("SELECT * FROM reco_table WHERE trackName LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<RecoMoviesResponseResult>>

}