package com.hoang.moviedblearning.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @SerializedName("vote_count")
    var voteCount: Int? = 0,
    @SerializedName("id")
    var id: String = "",
    @SerializedName("video")
    var video: Boolean? = null,
    @SerializedName("vote_average")
    var voteAverage: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("popularity")
    var popularity: String? = null,
    @SerializedName("poster_path")
    var posterPath: String? = null,
    @SerializedName("original_title")
    var originalTitle: String? = null,
    @SerializedName("backdrop_path")
    var backdropPath: String? = null,
    @SerializedName("adult")
    var adult: Boolean? = false,
    @SerializedName("overview")
    var overview: String? = null,
    @SerializedName("release_date")
    var releaseDate: String? = null
) : Parcelable