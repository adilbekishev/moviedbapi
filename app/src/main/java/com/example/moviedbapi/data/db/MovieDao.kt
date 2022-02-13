package com.example.moviedbapi.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviedbapi.data.models.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movie: Movie): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun upsertList(movies: List<Movie>)

    @Query("SELECT * FROM MOVIES LIMIT :limit OFFSET :offset")
    suspend fun getAllMovies(limit: Int, offset: Int): List<Movie>

    @Query("SELECT * FROM MOVIES WHERE movieId=:id")
    suspend fun getSingleMovie(id: Int?): Movie
}
