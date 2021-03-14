package com.movieflux.utils

import android.graphics.Bitmap
import androidx.palette.graphics.Palette

object PaletteUtils {
    suspend fun getPredominantColor(bitmap: Bitmap) = Palette.from(bitmap).generate()
}