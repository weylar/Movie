package com.android.movie.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.android.movie.database.DatabaseMovie
import com.android.movie.repository.MovieRepository
import javax.inject.Inject

class HomeViewModel
@Inject constructor(
    private val movieRepository:
    MovieRepository
) :
    ViewModel() {

    suspend fun getMovie(): LiveData<List<DatabaseMovie>>? {
        return movieRepository.getMovies()
    }
}