package com.movieflux.iu.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.movieflux.data.entity.Movie
import com.movieflux.databinding.ItemMovieBinding
import com.movieflux.utils.GlideUtils.load
import com.movieflux.utils.TheMoviesDBUtils

class MovieAdapter(private val onClick: (Movie) -> Unit) : ListAdapter<Movie, MovieAdapter.ViewHolder>(Movie.MovieDiffUtil) {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemMovieBinding.inflate(layoutInflater, viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(movie: Movie) {
            binding.apply {
                TheMoviesDBUtils.getUrlImage(movie.posterPath)
                    .load(imageView = ivMovie, requestOptions = RequestOptions().centerCrop())

                tvTitle.text = movie.title

                root.setOnClickListener {
                    onClick(movie)
                }
            }
        }
    }
}