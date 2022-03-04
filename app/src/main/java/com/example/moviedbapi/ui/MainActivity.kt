package com.example.moviedbapi.ui

import android.content.ContentResolver
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.moviedbapi.data.db.MovieDatabase
import com.example.moviedbapi.data.repository.MovieRepository
import com.example.moviedbapi.databinding.ActivityMainBinding
import com.example.moviedbapi.ui.adapter.MovieRecyclerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val moviesRepository = MovieRepository(MovieDatabase(this))
        val viewModelProviderFactory = MoviesViewModelProviderFactory(moviesRepository)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(MoviesViewModel::class.java)

        supportFragmentManager.beginTransaction()
            .replace(mBinding.fragmentContainer.id, MoviesFragment.newInstance())
            .commit()
    }
}
