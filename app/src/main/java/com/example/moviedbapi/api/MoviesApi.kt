package com.example.moviedbapi.api

import com.example.moviedbapi.models.Popular
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("3/movie/popular")
    suspend fun getPopular(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Popular
}