package com.example.moviedbapi.utils

sealed class DataState {
    abstract fun show(): String

    data class Success(val data: String) : DataState() {
        override fun show() = data
    }

    data class Error(val message: String) : DataState() {
        override fun show() = message
    }

    object Progress : DataState() {
        override fun show() = "progress"
    }
}