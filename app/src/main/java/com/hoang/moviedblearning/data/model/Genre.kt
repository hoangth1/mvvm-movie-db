package com.hoang.moviedblearning.data.model

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    var listMovie: ArrayList<Movie> = arrayListOf()
)
