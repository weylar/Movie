package com.android.movie.view.popularMovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.movie.database.DatabaseMovie
import com.android.movie.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PopularMovieViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Main + viewModelJob)



    suspend fun getMovie(): LiveData<List<DatabaseMovie>>? {
        return movieRepository.getMovies()
    }


    fun setFavorite(id: Long) {
        uiScope.launch {
           movieRepository.setFavoriteMovie(id)
        }
    }

    fun removeFavorite(id: Long) {
        uiScope.launch {
            movieRepository.removeFavoriteMovie(id)
        }
    }

}