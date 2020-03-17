package com.android.movie.view.movieDetails

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.movie.database.DatabaseMovie
import com.android.movie.model.Result
import com.android.movie.repository.MovieRepository
import com.android.movie.repository.TrailerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailFragmentViewModel
    @Inject constructor(
        private var movieRepository: MovieRepository) : ViewModel() {



    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
//    private val _movie: MutableLiveData<DatabaseMovie> by lazy {
//       // getMovie()
//    }
//
//    val movie:  LiveData<DatabaseMovie>
//        get() = _movie


     fun getMovie(movies: DatabaseMovie): MutableLiveData<DatabaseMovie>{
        val movie = MutableLiveData<DatabaseMovie>()
        movie.value = movies
        return movie
    }

    private suspend fun getMovieTrailers(id: Long){
        TrailerRepository().getMovieTrailers(id)
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

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}