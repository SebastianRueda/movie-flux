package com.movieflux.iu.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.movieflux.base.BaseViewModel
import com.movieflux.data.entity.Movie
import com.movieflux.data.remote.ApiClient
import com.movieflux.data.remote.response.GetPopularResponse
import com.movieflux.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : BaseViewModel(application) {
    val moviesLiveData = MutableLiveData<Result<List<Movie>>>()

    fun getMovies() {
        moviesLiveData.postValue(Result.loading())

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiClient.getInstance().getPopularMovies()
                moviesLiveData.postValue(Result.success(response.results ?: listOf()))
            } catch (e: Exception) {
                moviesLiveData.postValue(Result.error(e.message ?: "Error"))
            }
        }
    }
}