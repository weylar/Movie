package com.android.movie.dagger.home

import android.app.Application
import androidx.recyclerview.widget.GridLayoutManager
import com.android.movie.database.MovieDatabase
import com.android.movie.database.MovieDatabaseDao
import com.android.movie.network.MovieApi
import com.android.movie.repository.MovieRepository
import com.android.movie.source.MovieLocalDataSource
import com.android.movie.source.MovieRemoteDataSource
import dagger.Module
import dagger.Provides


@Module
abstract class MainModule {

    companion object {


        @Provides
        fun provideMovieRepository(
            movieLocalDataSource: MovieLocalDataSource,
            movieRemoteDataSource: MovieRemoteDataSource
        ): MovieRepository {
            return MovieRepository(movieLocalDataSource, movieRemoteDataSource)
        }


        @Provides
        fun provideMovieLocalDataSource(movieDatabaseDao: MovieDatabaseDao): MovieLocalDataSource {
            return MovieLocalDataSource(movieDatabaseDao)
        }

        @Provides
        fun provideMovieRemoteDataSource(movieApi: MovieApi): MovieRemoteDataSource {
            return MovieRemoteDataSource(movieApi)
        }




    }
}