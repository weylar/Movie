package com.android.movie.source


import androidx.lifecycle.LiveData
import com.android.movie.database.DatabaseMovie
import com.android.movie.database.MovieDatabaseDao
import com.android.movie.model.Trailers
import com.android.movie.network.Movie
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject


class MovieLocalDataSource
@Inject constructor(var database: MovieDatabaseDao) : MoviesDataSource {


    override suspend fun getRemoteMovies(page: Int): Movie? {
        return null
    }

    override suspend fun getMovieTrailer(id: Long): Trailers? {
        return null
    }

    override suspend fun getLocalMovies(): LiveData<List<DatabaseMovie>>? {
        return withContext(IO) {
            database.getAll()
        }

    }

    override suspend fun getLocalFavoriteMovies(): LiveData<List<DatabaseMovie>>? {
        return database.getFavorite()
    }

    override suspend fun setLocalFavorite(id: Long) {
        val updateId = database.favorite(key = id)
        /*return Transformations.map(database.getSingle(updateId)){
            it.id
        }*/
    }

    override suspend fun removeLocalFavorite(id: Long) {
        val updatedId = database.unFavorite(key = id)
        /*return Transformations.map(database.getSingle(updatedId)){
            it.id
        }*/
    }

    override suspend fun saveLocalMovies(vararg databaseMovie: DatabaseMovie) {
        database.insertAll(*databaseMovie)
    }


}

