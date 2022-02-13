package com.example.moviedbapi.data.network

import com.example.moviedbapi.data.models.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("3/movie/popular")
    suspend fun getPopular(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): MoviesResponse
}