package com.hoang.moviedblearning.data.response

import com.google.gson.annotations.SerializedName
import com.hoang.moviedblearning.data.model.Movie

class DetailGenreResponse(
    @SerializedName("page")
    var page: String? = null,
    @SerializedName("total_pages")
    var totalPage: Int? = null,
    @SerializedName("results")
    var listMovie: List<Movie>? = null
)