package com.hoang.moviedblearning.ui.screen.fragment.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.hoang.moviedblearning.data.model.Genre
import com.hoang.moviedblearning.data.repository.Repository
import com.hoang.moviedblearning.data.response.GenreResponse
import com.hoang.moviedblearning.rx.AppSchedulerProvider
import com.hoang.moviedblearning.ui.base.BaseViewModel
import com.matech.yogahealthfitness.rx.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: Repository,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {
    val listGenre = MutableLiveData<List<Genre>>()
    val isLoading = MutableLiveData<Boolean>()
    var index = -1
    fun getGenres() {
        addDisposable(
            repository.getGenres()
                .flatMap {
                    listGenre.postValue(it.genres)
                    Observable.fromIterable(it.genres)
                }.flatMap {
                    repository.getMovieByGenre(it.id ?: "")
                }
                .subscribeOn(schedulerProvider.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isLoading.value = true }
                .doOnComplete {
                    val genres = arrayListOf<Genre>()
                    genres.addAll(listGenre.value ?: arrayListOf())
                    listGenre.value = genres
                }
                .doAfterTerminate {
                    isLoading.value = false
                }

                .subscribe({
                    index++
                    listGenre.value?.get(index)?.listMovie?.addAll(it.listMovie ?: arrayListOf())

                }, {

                })
        )
    }


}