package com.example.moviedbapi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.moviedbapi.ui.adapter.MovieRecyclerAdapter
import com.example.moviedbapi.data.repository.MovieRepository
import com.example.moviedbapi.databinding.ActivityMainBinding
import com.example.moviedbapi.data.db.MovieDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAdapter: MovieRecyclerAdapter
    lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val moviesRepository = MovieRepository(MovieDatabase(this))
        val viewModelProviderFactory = MoviesViewModelProviderFactory(moviesRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MoviesViewModel::class.java)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(mBinding.fragmentContainer.id, MoviesFragment.newInstance())
                .commitAllowingStateLoss()
        }
    }
}
