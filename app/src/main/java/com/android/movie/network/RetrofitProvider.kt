package com.android.movie.network


import com.android.movie.util.Utility
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {


    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Utility.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideMovieApi(): MovieApi {
        return retrofit().create(MovieApi::class.java)
    }

}