package com.example.moviedbapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.moviedbapi.R
import com.example.moviedbapi.data.models.Movie
import com.example.moviedbapi.databinding.FragmentMovieDetailBinding
import com.example.moviedbapi.utils.Constants

class MovieDetailFragment : Fragment() {

    private lateinit var mBinding: FragmentMovieDetailBinding
    private lateinit var movie: Movie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movie = arguments?.getSerializable("movie") as Movie
        mBinding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.apply {
            titleTextView.text = movie.title
            Glide.with(requireContext()).load(Constants.IMAGE_URL + movie.poster_path)
                .into(imageView)
            descriptionTextView.text = movie.overview
            voteTextView.text = movie.vote_average.toString()

            if (movie.favourite) Glide.with(requireContext()).load(R.drawable.star_active)
                .into(favouriteImageView)
            else Glide.with(requireContext()).load(R.drawable.star)
                .into(favouriteImageView)
        }
    }

    companion object {

        fun newInstance(movie: Movie) = MovieDetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable("movie", movie)
            }
        }
    }
}