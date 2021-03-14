package com.movieflux.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object GlideUtils {
    fun String?.load(imageView: ImageView, placeholder: Int = android.R.color.transparent,
                    requestOptions: RequestOptions? = null) {
        Glide.with(imageView)
            .load(this ?: placeholder)
            .placeholder(placeholder)
            .apply {
                if (requestOptions != null) {
                    apply(requestOptions)
                }
            }
            .into(imageView)
    }

    fun String?.getBitmap(context: Context) = Glide.with(context).asBitmap().load(this ?: android.R.color.transparent).submit().get()
}