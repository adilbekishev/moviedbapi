package com.example.moviedbapi.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "movies"
)
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val budget: Int?,
    val overview: String?,
    val poster_path: String?,
    val title: String?,
    val vote_average: Double?,
)