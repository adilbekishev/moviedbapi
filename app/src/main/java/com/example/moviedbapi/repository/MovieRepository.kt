package com.example.moviedbapi.repository

import com.example.moviedbapi.api.RetrofitInstance
import com.example.moviedbapi.db.MovieDatabase
import com.example.moviedbapi.models.Movie
import com.example.moviedbapi.utils.Constants

class MovieRepository(
    val db: MovieDatabase
) {

    suspend fun getMovies(pageNumber: Int) =
        RetrofitInstance.service.getPopular(Constants.api_key, pageNumber)

    suspend fun upsertList(movies: List<Movie>) = db.getMovieDao().upsertList(movies)

    suspend fun upsert(movie: Movie) = db.getMovieDao().upsert(movie)

    fun getSavedMovies() = db.getMovieDao().getAllMovies()

    suspend fun getSingleMovie(id: Int?) = db.getMovieDao().getSingleMovie(id)

}