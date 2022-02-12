package com.example.moviedbapi.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Popular(
    val page: Int,
    @SerializedName("results")
    @Expose
    val movies: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)