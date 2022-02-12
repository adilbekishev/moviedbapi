package com.example.moviedbapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedbapi.databinding.ItemMovieBinding
import com.example.moviedbapi.models.Movie
import com.example.moviedbapi.utils.Constants

class MovieRecyclerAdapter(private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movies[position])

    override fun getItemCount() = movies.size

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            Glide.with(binding.root.context).load(Constants.IMAGE_URL + movie.poster_path)
                .into(binding.imageView)

            binding.voteTextView.text = "$position"
            binding.titleTextView.text = movie.title
        }
    }
}