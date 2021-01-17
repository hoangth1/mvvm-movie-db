package com.hoang.moviedblearning.data.response

import com.google.gson.annotations.SerializedName
import com.hoang.moviedblearning.data.model.Movie

class MovieResponse(
    @SerializedName("results")
    var listMovie: List<Movie>,
    @SerializedName("page")
    var page: Int = 0,
    @SerializedName("total_results")
    var totalResult: Int = 0,
    @SerializedName("total_pages")
    var totalPage: Int = 0
)