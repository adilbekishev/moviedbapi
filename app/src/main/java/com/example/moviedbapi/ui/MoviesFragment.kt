package com.example.moviedbapi.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedbapi.MainActivity
import com.example.moviedbapi.adapter.MovieRecyclerAdapter
import com.example.moviedbapi.databinding.FragmentMoviesBinding
import com.example.moviedbapi.models.Movie
import com.example.moviedbapi.utils.DataState
import com.example.moviedbapi.utils.PaginationScrollListener

class MoviesFragment : Fragment() {

    private lateinit var mBinding: FragmentMoviesBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var movies: MutableList<Movie>
    private lateinit var mAdapter: MovieRecyclerAdapter
    private var isLoading = false
    private var isLastPage = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMoviesBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecycler()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = (activity as MainActivity).viewModel

        viewModel.state.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataState.Success -> hideProgressBar()
                is DataState.Progress -> showProgressBar()
                is DataState.Error -> {
                    hideProgressBar()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.getSavedMovies().observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) viewModel.getMovies(1)
            else {
                movies.clear()
                movies.addAll(it)
                Log.d("ggg", "observe Cache : ${it.size}, movies size: ${movies.size}")
                //Toast.makeText(requireContext(), "${movies.size}", Toast.LENGTH_SHORT).show()
                mAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun setUpRecycler() {
        movies = ArrayList()
        mAdapter = MovieRecyclerAdapter(movies.sortedBy { it.id })
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        mBinding.moviesRecyclerView.apply {
            adapter = mAdapter
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
                override fun loadMoreItems() {
                    isLoading = true
                    Log.d("ggg", "loadMoreItems: ")
                    viewModel.getMovies(movies.size)
                }

                override fun isLastPage(): Boolean {
                    return isLastPage
                }

                override fun isLoading(): Boolean {
                    return isLoading
                }
            })
        }
    }

    private fun showProgressBar() {
        mBinding.progressBar.visibility = View.VISIBLE
        isLoading = true
    }

    private fun hideProgressBar() {
        mBinding.progressBar.visibility = View.GONE
        isLoading = false
    }

    companion object {
        fun newInstance() = MoviesFragment()
    }
}