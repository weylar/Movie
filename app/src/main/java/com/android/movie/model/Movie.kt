package com.android.movie.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize




@Parcelize
data class Result(
    val popularity: Double?,
    var isFavorite: Boolean,
    val voteCount: Long?,
    val video: Boolean?,
    val posterPath: String?,
    val id: Long,
    val adult: Boolean?,
    val backdropPath: String?,
    val originalTitle: String?,
    val title: String?,
    val voteAverage: Double?,
    val overview: String?,
    val releaseDate: String?
): Parcelable




