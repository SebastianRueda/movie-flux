package com.movieflux.utils

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}
fun View.gone() {
    visibility = View.GONE
}

fun FragmentManager.replace(containerId: Int, fragment: Fragment, addBackStack: Boolean = false) {
    beginTransaction().replace(containerId, fragment)
            .apply {
                if (addBackStack) {
                    addToBackStack(null)
                }
            }
            .commit()
}