package com.android.movie.source


import androidx.lifecycle.LiveData
import com.android.movie.database.DatabaseMovie
import com.android.movie.model.Trailers
import com.android.movie.network.Movie
import com.android.movie.network.RetrofitProvider
import timber.log.Timber


object MovieRemoteDataSource : MoviesDataSource {


    override suspend fun getRemoteMovies(page: Int): Movie? {
        try {
            return RetrofitProvider.provideMovieApi().getMoviesAsync(page = page)
        } catch (t: Throwable) {
            Timber.i(t.fillInStackTrace())
        }
        return null

    }

    override suspend fun getMovieTrailer(id: Long): Trailers? {
        try {
            return RetrofitProvider.provideMovieApi().getMoviesTrailerAsync(id)
        } catch (t: Throwable) {
            Timber.i(t.fillInStackTrace())
        }
        return null
    }


    override suspend fun getLocalMovies(): LiveData<List<DatabaseMovie>>? {
        return null
    }

    override suspend fun getLocalFavoriteMovies(): LiveData<List<DatabaseMovie>>? {
        return null
    }

    override suspend fun setLocalFavorite(id: Long) {
    }

    override suspend fun removeLocalFavorite(id: Long) {

    }

    override suspend fun saveLocalMovies(vararg databaseMovie: DatabaseMovie) {

    }
}

