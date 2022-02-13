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


    fun getMovies(listSize: Int) = viewModelScope.launch {
        val page = listSize / Constants.QUERY_PAGE_SIZE + 1
        state.value = DataState.Progress
        try {
            val test = Constants.QUERY_PAGE_SIZE * (page - 2)
            var offset = 0
            if (test < 0) {
                offset = 0
            } else {
                offset = test
            }
            val data = getSavedMovies(Constants.QUERY_PAGE_SIZE, offset)
            val data2 = repository.getSavedMovies2()

            Log.d(
                "ggg",
                "cachedData size: ${data.size}, listSize:$listSize, offset: $offset, data2:${data2.size}"
            )
            //подумать
            if (data.isEmpty() or (listSize != test + data.size)) {
                val data = getMoviesApi(page)
                _movies.value = data
                saveMovies(data)
                state.value = DataState.Success
                Log.d("ggg", "from api")
            } else {
                _movies.value = data
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


}