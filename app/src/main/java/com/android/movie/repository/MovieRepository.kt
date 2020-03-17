package com.android.movie.repository


import android.app.Application
import androidx.lifecycle.LiveData
import com.android.movie.database.DatabaseMovie
import com.android.movie.network.asDatabaseModel
import com.android.movie.source.MovieLocalDataSource
import com.android.movie.source.MovieRemoteDataSource
import com.android.movie.util.Utility.PAGES
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named


class MovieRepository @Inject constructor(
    private val movieLocalSource: MovieLocalDataSource,
    private val movieRemoteSource: MovieRemoteDataSource){


    suspend fun getMovies(): LiveData<List<DatabaseMovie>>? {
        return movieLocalSource.getLocalMovies()
    }

    suspend fun refreshMovies() {
        Timber.i("Refresh was called")
        withContext(IO) {
            for (page in 1..PAGES) {
                val movies = movieRemoteSource.getRemoteMovies(page)
                movies?.let {
                    movieLocalSource.saveLocalMovies(*movies.asDatabaseModel())
                }
            }
        }
    }


    suspend fun getFavoriteMovies(): LiveData<List<DatabaseMovie>>? {
        return withContext(IO) {
            movieLocalSource.getLocalFavoriteMovies()
        }

    }

    suspend fun setFavoriteMovie(id: Long) {
        withContext(IO) {
            movieLocalSource.setLocalFavorite(id)
        }

    }

    suspend fun removeFavoriteMovie(id: Long) {
        withContext(IO) {
            movieLocalSource.removeLocalFavorite(id)

        }

    }


}


