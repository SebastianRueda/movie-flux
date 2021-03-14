package com.movieflux.utils

object TheMoviesDBUtils {
    private const val IMG_URL_BASE = "https://image.tmdb.org/t/p/w300"

    fun getUrlImage(path: String?): String? {
        return if (!path.isNullOrEmpty())
            IMG_URL_BASE + path
        else
            null
    }
}