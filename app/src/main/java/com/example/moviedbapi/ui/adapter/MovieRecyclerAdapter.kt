package com.example.moviedbapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedbapi.R
import com.example.moviedbapi.data.models.Movie
import com.example.moviedbapi.databinding.ItemMovieBinding
import com.example.moviedbapi.utils.Constants

class MovieRecyclerAdapter(private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder>() {

    var onItemClick: ((Movie) -> Unit)? = null
    var onFavouriteClick: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movies[position])

    override fun getItemCount() = movies.size

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                Glide.with(root.context).load(Constants.IMAGE_URL + movie.poster_path)
                    .into(imageView)
                voteTextView.text = "$position"
                titleTextView.text = movie.title
                root.setOnClickListener {
                    onItemClick?.invoke(movie)
                }

                favouriteImageView.setOnClickListener {
                    onFavouriteClick?.invoke(movie)
                }

                    if (movie.favourite) Glide.with(root.context).load(R.drawable.star_active)
                        .into(favouriteImageView)
                    else Glide.with(root.context).load(R.drawable.star)
                        .into(favouriteImageView)
            }
        }
    }
}