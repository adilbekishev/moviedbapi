package com.example.moviedbapi.data.repository

import com.example.moviedbapi.data.network.RetrofitInstance
import com.example.moviedbapi.data.db.MovieDatabase
import com.example.moviedbapi.data.models.Movie
import com.example.moviedbapi.utils.Constants

class MovieRepository(
    val database: MovieDatabase
) {

    suspend fun getMovies(pageNumber: Int) =
        RetrofitInstance.service.getPopular(Constants.api_key, pageNumber)

    suspend fun upsertList(movies: List<Movie>) = database.getMovieDao().upsertList(movies)

    suspend fun upsert(movie: Movie) = database.getMovieDao().upsert(movie)

    suspend fun getSavedMovies(limit: Int, offset: Int) = database.getMovieDao().getAllMovies(limit, offset)

    suspend fun getSingleMovie(id: Int?) = database.getMovieDao().getSingleMovie(id)

}