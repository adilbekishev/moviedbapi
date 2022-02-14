package com.example.moviedbapi.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "movies"
)
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val movieId: Int? = null,
    val id: Int?,
    val budget: Int?,
    val overview: String?,
    val poster_path: String?,
    val title: String?,
    val vote_average: Double?,
    var favourite: Boolean = false
) : Serializable