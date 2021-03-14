package com.movieflux.iu.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.movieflux.R
import com.movieflux.base.BaseFragment
import com.movieflux.databinding.FragmentDetailBinding
import com.movieflux.databinding.FragmentHomeBinding
import com.movieflux.iu.MainActivity
import com.movieflux.utils.GridSpacingItemDecoration
import com.movieflux.utils.gone
import com.movieflux.utils.visible

class HomeFragment : BaseFragment() {
    companion object {
        fun newInstance() =
                HomeFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    private lateinit var binding: FragmentHomeBinding
    private val viewmodel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()

        binding.apply {
            val span = 2
            rvMovies.apply {
                layoutManager = GridLayoutManager(context, span)
                addItemDecoration(GridSpacingItemDecoration(span, resources.getDimensionPixelSize(R.dimen.margin_item), true))
                this@HomeFragment.adapter = MovieAdapter() {
                    if (activity is MainActivity) {
                        (activity as MainActivity).openDetail(it)
                    }
                }
                adapter = this@HomeFragment.adapter
            }
        }

        viewmodel.getMovies()
    }

    private fun setupObservers() {
        viewmodel.moviesLiveData.observe(viewLifecycleOwner) {
            binding.apply {
                it?.setState({
                    pbLoading.gone()
                    rvMovies.visible()

                    adapter.submitList(it)
                }, {
                    pbLoading.gone()
                    view?.let { it1 -> Snackbar.make(it1, it, Snackbar.LENGTH_SHORT).show() }
                }, {
                    pbLoading.visible()
                    rvMovies.gone()
                })
            }
        }
    }
}