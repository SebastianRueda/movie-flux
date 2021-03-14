package com.movieflux.iu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.movieflux.R
import com.movieflux.base.BaseActivity
import com.movieflux.data.entity.Movie
import com.movieflux.databinding.ActivityMainBinding
import com.movieflux.iu.detail.DetailFragment
import com.movieflux.iu.home.HomeFragment
import com.movieflux.iu.home.HomeViewModel
import com.movieflux.utils.replace

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            supportFragmentManager.replace(R.id.flContainer, HomeFragment.newInstance())
        }
    }

    fun openDetail(movie: Movie) {
        supportFragmentManager.replace(R.id.flContainer, DetailFragment.newInstance(movie), true)
    }
}