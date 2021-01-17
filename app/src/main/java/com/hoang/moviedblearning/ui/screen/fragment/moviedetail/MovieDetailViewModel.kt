package com.hoang.moviedblearning.ui.screen.fragment.moviedetail

import androidx.lifecycle.MutableLiveData
import com.hoang.moviedblearning.data.model.Movie
import com.hoang.moviedblearning.ui.base.BaseViewModel
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor() : BaseViewModel() {
    val movie = MutableLiveData<Movie>()
}