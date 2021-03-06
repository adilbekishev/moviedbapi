package com.example.moviedbapi.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val page: Int,
    @SerializedName("results")
    @Expose
    val movies: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)