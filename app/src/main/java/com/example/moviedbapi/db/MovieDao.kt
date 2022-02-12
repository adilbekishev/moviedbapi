package com.example.moviedbapi.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviedbapi.models.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movie: Movie): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun upsertList(movies: List<Movie>)

    @Query("SELECT * FROM MOVIES")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM MOVIES WHERE id=:id")
    suspend fun getSingleMovie(id: Int?): Movie
}
