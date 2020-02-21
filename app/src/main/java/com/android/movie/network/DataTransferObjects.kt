package com.android.movie.network

import com.android.movie.database.DatabaseMovie
import com.android.movie.model.Result
import com.google.gson.annotations.SerializedName


data class Movie(
    val page: Long,

    @SerializedName("total_results")
    val totalResults: Long,

    @SerializedName("total_pages")
    val totalPages: Long,

    val results: List<NetworkResult>
)

data class NetworkResult(

    val popularity: Double?,
    var isFavorite: Boolean,
    @SerializedName("vote_count")
    val voteCount: Long?,
    val video: Boolean?,
    @SerializedName("poster_path")
    val posterPath: String?,
    val id: Long,
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    val overview: String?,
    @SerializedName("release_date")
    val releaseDate: String?
)

fun Movie.asDomainModel(): List<Result> {
    return results.map {
        Result(
            popularity = it.popularity,
            isFavorite = it.isFavorite,
            voteCount = it.voteCount,
            video = it.video,
            posterPath = it.posterPath,
            id = it.id,
            adult = it.adult,
            backdropPath = it.backdropPath,
            originalTitle = it.originalTitle,
            title = it.title,
            voteAverage = it.voteAverage,
            overview = it.overview,
            releaseDate = it.releaseDate
        )
    }
}

fun Movie.asDatabaseModel(): Array<DatabaseMovie> {
    return results.map {
        DatabaseMovie(
            popularity = it.popularity,
            isFavorite = it.isFavorite,
            voteCount = it.voteCount,
            video = it.video,
            posterPath = it.posterPath,
            id = it.id,
            adult = it.adult,
            backdropPath = it.backdropPath,
            originalTitle = it.originalTitle,
            title = it.title,
            voteAverage = it.voteAverage,
            overview = it.overview,
            releaseDate = it.releaseDate
        )
    }.toTypedArray()
}