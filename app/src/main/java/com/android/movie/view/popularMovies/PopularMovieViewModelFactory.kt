package com.android.movie.view.popularMovies


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.movie.repository.MovieRepository

class PopularMovieViewModelFactory(
    private val dataSource: MovieRepository
) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PopularMovieViewModel::class.java)) {
            return PopularMovieViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

