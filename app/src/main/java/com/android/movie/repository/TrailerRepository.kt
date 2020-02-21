package com.android.movie.repository

import com.android.movie.model.Trailers
import com.android.movie.source.MovieRemoteDataSource

class TrailerRepository {


    suspend fun getMovieTrailers(id: Long): Trailers? {
        return MovieRemoteDataSource.getMovieTrailer(id)


    }
}