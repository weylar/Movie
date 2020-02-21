package com.android.movie.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.android.movie.database.DatabaseMovie
import com.android.movie.repository.MovieRepository

class HomeFragmentViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    suspend fun getMovie(): LiveData<List<DatabaseMovie>>? {
        return movieRepository.getMovies()
    }
}