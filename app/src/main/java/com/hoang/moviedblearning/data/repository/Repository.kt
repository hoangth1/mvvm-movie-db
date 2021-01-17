package com.hoang.moviedblearning.data.repository

import com.hoang.moviedblearning.data.response.DetailGenreResponse
import com.hoang.moviedblearning.data.response.GenreResponse
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Singleton

interface Repository {
    fun getGenres(): Observable<GenreResponse>

    fun getMovieByGenre(genreId: String, page: Int = 1): Observable<DetailGenreResponse>
}