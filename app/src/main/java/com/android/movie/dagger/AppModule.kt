package com.android.movie.dagger

import android.app.Application
import androidx.work.WorkerParameters
import com.android.movie.database.MovieDatabase
import com.android.movie.database.MovieDatabaseDao
import com.android.movie.network.MovieApi
import com.android.movie.repository.MovieRepository
import com.android.movie.util.Utility
import com.android.movie.work.RefreshDataWorker
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule {

    companion object {

        @Singleton
        @Provides
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Utility.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Singleton
        @Provides
        fun provideMovieApi(retrofit: Retrofit): MovieApi {
            return retrofit.create(MovieApi::class.java)
        }

        @Singleton
        @Provides
        fun provideDatabaseDao(application: Application): MovieDatabaseDao {
            return MovieDatabase.getInstance(application).movieDatabaseDao
        }

        @Singleton
        @Provides
        fun providePicasso(): Picasso{
            return Picasso.get()
        }




    }
}