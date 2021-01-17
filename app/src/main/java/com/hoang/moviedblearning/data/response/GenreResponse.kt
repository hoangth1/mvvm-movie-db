package com.hoang.moviedblearning.data.response

import com.google.gson.annotations.SerializedName
import com.hoang.moviedblearning.data.model.Genre

class GenreResponse(
    @SerializedName("genres")
    var genres: List<Genre>
)