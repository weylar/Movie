package com.android.movie.source


import androidx.lifecycle.LiveData
import com.android.movie.database.DatabaseMovie
import com.android.movie.model.Trailers
import com.android.movie.network.Movie


interface MoviesDataSource {


    suspend fun getLocalMovies(): LiveData<List<DatabaseMovie>>?
    suspend fun getLocalFavoriteMovies(): LiveData<List<DatabaseMovie>>?
    suspend fun setLocalFavorite(id: Long)
    suspend fun removeLocalFavorite(id: Long)
    suspend fun saveLocalMovies(vararg databaseMovie: DatabaseMovie)
    suspend fun getRemoteMovies(page: Int): Movie?
    suspend fun getMovieTrailer(id: Long): Trailers?

}