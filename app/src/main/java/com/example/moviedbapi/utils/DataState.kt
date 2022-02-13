package com.example.moviedbapi.utils

sealed class DataState {
    data class Error(val message: String) : DataState()
    object Success : DataState()
    object Progress : DataState()
}