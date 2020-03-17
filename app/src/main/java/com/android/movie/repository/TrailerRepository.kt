package com.android.movie.repository

import com.android.movie.model.Trailers
import com.android.movie.source.MovieRemoteDataSource
import javax.inject.Inject

class TrailerRepository {

    @Inject
    lateinit var movieRemoteDataSource: MovieRemoteDataSource

    suspend fun getMovieTrailers(id: Long): Trailers? {
        return movieRemoteDataSource.getMovieTrailer(id)


    }
}