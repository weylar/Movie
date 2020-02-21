package com.android.movie.view.favoriteMovies


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.movie.repository.MovieRepository


class FavoriteMovieFragmentViewModelFactory(private val repository: MovieRepository) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteMovieFragmentViewModel::class.java)) {
            return FavoriteMovieFragmentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

