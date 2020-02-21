package com.android.movie.view.movieDetails


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.movie.database.DatabaseMovie
import com.android.movie.model.Result

class MovieDetailViewModelFactory(private val dataSource: DatabaseMovie, val app: Application) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailFragmentViewModel::class.java)) {
            return MovieDetailFragmentViewModel(dataSource, app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

