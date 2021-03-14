package com.movieflux.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    protected val context: Context
        get() {
            return getApplication<Application>().baseContext
        }

    protected fun getString(id: Int): String = context.getString(id)
}