package com.example.moviedbapi.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedbapi.data.models.Movie
import com.example.moviedbapi.data.network.RetrofitInstance
import com.example.moviedbapi.data.repository.MovieRepository
import com.example.moviedbapi.utils.Constants
import com.example.moviedbapi.utils.Constants.QUERY_PAGE_SIZE
import com.example.moviedbapi.utils.DataState
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies
    val state = MutableLiveData<DataState>()

    fun getMovies(listSize: Int) = viewModelScope.launch {
        state.value = DataState.Progress
        try {
            val page = listSize / QUERY_PAGE_SIZE + 1
            val offset = if (page == 1) 0 else (page - 1) * QUERY_PAGE_SIZE

            val cachedData = getSavedMovies(QUERY_PAGE_SIZE, offset)

            if (cachedData.isEmpty()) {
                val data = getMoviesApi(page)
                _movies.value = data
                saveMovies(data)
                state.value = DataState.Success
                Log.d("ggg", "from api")
            } else {
                _movies.value = cachedData
                state.value = DataState.Success
                Log.d("ggg", "from cache")
            }
        } catch (e: Exception) {
            Log.d("ggg", e.message ?: "Error")
            state.value = DataState.Error(e.message ?: "Error")
        }
    }

    private suspend fun saveMovies(movies: List<Movie>) = repository.upsertList(movies)

    private suspend fun getSavedMovies(limit: Int, offset: Int) =
        repository.getSavedMovies(limit, offset)

    private suspend fun getMoviesApi(page: Int) =
        RetrofitInstance.service.getPopular(Constants.api_key, page).movies

    fun upsertMovie(movie: Movie) = viewModelScope.launch {
        repository.upsert(movie)

    }
}