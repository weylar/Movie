package com.android.movie.model

data class Trailers(
    val id: Int,
    val results: List<Result>
)

data class Trailer(
    val id: String,
    val iso_3166_1: String,
    val iso_639_1: String,
    val key: String,
    val name: String,
    val site: String,
    val size: Int,
    val type: String
)