package com.android.movie.database


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.movie.model.Result
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie_table")
data class DatabaseMovie(
    val popularity: Double?,
    var isFavorite: Boolean,
    @ColumnInfo(name = "vote_count")
    val voteCount: Long?,
    val video: Boolean?,
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val adult: Boolean?,
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,
    @ColumnInfo(name = "original_title")
    val originalTitle: String?,
    val title: String?,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double?,
    val overview: String?,
    @ColumnInfo(name = "release_date")
    val releaseDate: String?
):Parcelable

fun List<DatabaseMovie>.asDomainModel(): List<Result> {
    return map {
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




