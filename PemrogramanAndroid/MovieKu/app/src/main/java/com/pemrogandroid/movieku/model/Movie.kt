package com.pemrogandroid.movieku.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.TypeConverters

import com.pemrogandroid.movieku.repository.IntegerListTypeConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_table")
data class Movie(
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null,
    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("video")
    @Expose
    var video: Boolean? = null,
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Float? = null,
    @SerializedName("title")
    @Expose
    var title: String? = null,
    @SerializedName("popularity")
    @Expose
    var popularity: Float? = null,
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null,
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null,
    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null,
    @SerializedName("genre_ids")
    @Expose
    var genreIds: List<Int>? = null,
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null,
    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null,
    @SerializedName("overview")
    @Expose
    var overview: String? = null,
    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null,
    var watched: Boolean = false
) {
    constructor(title: String, releaseDate: String, posterPath: String) : this(){
        this.title = title
        this.releaseDate = releaseDate
        this.posterPath = posterPath
    }
    constructor(
        voteCount: Int,
        video: Boolean,
        voteAverage: Float,
        title: String,
        popularity: Float,
        posterPath: String,
        originalTitle: String,
        originalLanguage: String,
        genreIds: List<Int>,
        backdropPath: String,
        adult: Boolean,
        releaseDate: String,
        overview: String,
    ) :this(){
        this.voteCount = voteCount
        this.video = video
        this.title = title
        this.voteAverage = voteAverage
        this.popularity = popularity
        this.posterPath = posterPath
        this.originalTitle = originalTitle
        this.originalLanguage = originalLanguage
        this.genreIds = genreIds
        this.adult = adult
        this.backdropPath = backdropPath
        this.releaseDate = releaseDate
        this.overview = overview
    }

}