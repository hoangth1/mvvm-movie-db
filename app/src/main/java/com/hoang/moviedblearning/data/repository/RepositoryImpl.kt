package com.hoang.moviedblearning.data.repository

import com.hoang.moviedblearning.data.api.AppApi
import com.hoang.moviedblearning.data.response.DetailGenreResponse
import com.hoang.moviedblearning.data.response.GenreResponse
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val appApi: AppApi) : Repository {
    override fun getGenres(): Observable<GenreResponse> {
        return appApi.getGenres()
    }

    override fun getMovieByGenre(genreId: String, page: Int): Observable<DetailGenreResponse> {
        return appApi.getMovieByGenre(genreId, page)
    }
}