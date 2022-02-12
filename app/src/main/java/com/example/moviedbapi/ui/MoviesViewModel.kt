package com.example.moviedbapi.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedbapi.api.RetrofitInstance
import com.example.moviedbapi.models.Movie
import com.example.moviedbapi.repository.MovieRepository
import com.example.moviedbapi.utils.Constants
import com.example.moviedbapi.utils.DataState
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies
    val state = MutableLiveData<DataState>()

    fun getMovies(listSize: Int) {
        viewModelScope.launch {
            val page = listSize / Constants.QUERY_PAGE_SIZE + 1
            Log.d("ggg", "called page: $page, listSize:$listSize")
            state.value = DataState.Progress
            try {
                val data = RetrofitInstance.service.getPopular(Constants.api_key, page)
                Log.d("ggg", "response ${data.movies.size}")
                saveMovies(data.movies)
                state.value = DataState.Success("Success")
                //repository.upsert(data.movies[0])
            } catch (e: Exception) {
                Log.d("ggg", e.message ?: "Error")
                state.value = DataState.Error("Error")
            }
        }
    }

    private suspend fun saveMovies(movies: List<Movie>) = repository.upsertList(movies)

    fun getSavedMovies() = viewModelScope.launch {
        state.value = DataState.Progress
        try {
            val data = repository.getSavedMovies()
            state.value = DataState.Success("Success")
        } catch (e: java.lang.Exception) {
            Log.d("ggg", e.message ?: "Error")
            state.value = DataState.Error("Error")
        }
    }
}