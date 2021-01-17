package com.hoang.moviedblearning.data.api

import com.hoang.moviedblearning.data.response.DetailGenreResponse
import com.hoang.moviedblearning.data.response.GenreResponse
import com.hoang.moviedblearning.utils.PARAM_PAGE
import com.hoang.moviedblearning.utils.PARAM_WITH_GENRE
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AppApi {
    @GET("genre/movie/list")
    fun getGenres(): Observable<GenreResponse>

    @GET("discover/movie")
    fun getMovieByGenre(
        @Query(PARAM_WITH_GENRE) genreId: String,
        @Query(PARAM_PAGE) page: Int
    ): Observable<DetailGenreResponse>
}