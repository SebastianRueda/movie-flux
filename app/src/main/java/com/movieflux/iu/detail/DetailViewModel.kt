package com.movieflux.iu.detail

import android.app.Application
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.movieflux.R
import com.movieflux.base.BaseViewModel
import com.movieflux.utils.GlideUtils
import com.movieflux.utils.GlideUtils.getBitmap
import com.movieflux.utils.PaletteUtils
import com.movieflux.utils.Result
import com.movieflux.utils.TheMoviesDBUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application) {
    val bitmapMutableLiveData = MutableLiveData<Result<Bitmap>>()
    val colorMutableLiveData = MutableLiveData<Result<Palette>>()

    fun getBitmap(url: String?) {
        bitmapMutableLiveData.postValue(Result.loading())
        viewModelScope.launch(Dispatchers.Default) {
            val bitmap = TheMoviesDBUtils.getUrlImage(url).getBitmap(context)
            bitmapMutableLiveData.postValue(Result.success(bitmap))
            getBackgroundColor(bitmap)
        }
    }

    fun getBackgroundColor(bitmap: Bitmap) {
        colorMutableLiveData.postValue(Result.loading())
        viewModelScope.launch(Dispatchers.Default) {
            val palette = PaletteUtils.getPredominantColor(bitmap)
            colorMutableLiveData.postValue(Result.success(palette))
        }
    }
}