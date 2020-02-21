package com.android.movie.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object Utility {
    const val BASE_URL = "https://api.themoviedb.org/4/"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
    const val API_KEY = "fb97e27952573c39dd8c56b40023750e"
    const val PAGES = 100
    const val YOUTUBE_API_KEY = ""

    fun hideSoftKeyBoard(context: Context, view: View) {
        try {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}