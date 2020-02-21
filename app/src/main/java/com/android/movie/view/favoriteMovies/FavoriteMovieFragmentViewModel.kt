package com.android.movie.view.favoriteMovies

import android.app.Application
import androidx.lifecycle.*
import com.android.movie.database.DatabaseMovie
import com.android.movie.model.Result
import com.android.movie.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FavoriteMovieFragmentViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Main + viewModelJob)

    var favoriteMovies: LiveData<List<DatabaseMovie>>? = MutableLiveData<List<DatabaseMovie>>()

    private var _itemRemoved: LiveData<Long> = MutableLiveData<Long>()

    val itemRemoved: LiveData<Long>
        get() = _itemRemoved

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() {
        uiScope.launch {
            favoriteMovies = movieRepository.getFavoriteMovies()

        }

    }

    fun removeFavorite(id: Long) {
        uiScope.launch {
             movieRepository.removeFavoriteMovie(id)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}