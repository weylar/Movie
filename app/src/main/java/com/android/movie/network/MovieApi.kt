package com.android.movie.network


import com.android.movie.model.Trailers
import com.android.movie.util.Utility
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    suspend fun getMoviesAsync(
        @Query("sort_by") filter: String = "popularity.desc",
        @Query("api_key") api: String = Utility.API_KEY,
        @Query("page") page: Int
    ): Movie

    @GET("movie/{id}/videos")
    suspend fun getMoviesTrailerAsync(
        @Path("id") id: Long,
        @Query("api_key") api: String = Utility.API_KEY
    ): Trailers

}