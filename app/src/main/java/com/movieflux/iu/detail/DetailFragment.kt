package com.movieflux.iu.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.movieflux.R
import com.movieflux.base.BaseFragment
import com.movieflux.data.entity.Movie
import com.movieflux.databinding.FragmentDetailBinding
import com.movieflux.databinding.FragmentHomeBinding
import com.movieflux.utils.ResultStatus

class DetailFragment : BaseFragment() {
    companion object {
        private const val ARG_MOVIE = "movie"

        fun newInstance(movie: Movie) =
                DetailFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(ARG_MOVIE, movie)
                    }
                }
    }

    private lateinit var binding: FragmentDetailBinding
    private lateinit var movie: Movie
    private val viewmodel by lazy { ViewModelProvider(this)[DetailViewModel::class.java] }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movie = arguments?.getParcelable(ARG_MOVIE) ?: return

        setupObs()

        binding.apply {
            tvTitle.text = movie.title ?: ""
            tvYear.text = movie.releaseDate?.split("-")?.get(0) ?: ""
            tvDescription.text = movie.overview
        }

        viewmodel.getBitmap(movie.posterPath)
    }

    private fun setupObs() {
        viewmodel.bitmapMutableLiveData.observe(viewLifecycleOwner) {
            it.setState({
                binding.apply {
                    Glide.with(ivMoviePoster).load(it).into(ivMoviePoster)
                    Glide.with(ivMovieBackground).load(it).into(ivMovieBackground)
                }
            }, {

            }, {

            })
        }

        viewmodel.colorMutableLiveData.observe(viewLifecycleOwner) {
            it.setState({
                binding.apply {
                    val dark = it.darkMutedSwatch?.rgb

                    if (dark != null) {
                        vBackground.setBackgroundColor(dark)
                    }

                    val light = it.lightVibrantSwatch?.rgb

                    if (light != null) {
                        tvTitle.setTextColor(light)
                        tvYear.setTextColor(light)
                        tvSubtitleDescription.setTextColor(light)
                        tvDescription.setTextColor(light)
                    }
                }
            }, {

            }, {

            })
        }
    }
}