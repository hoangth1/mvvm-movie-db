package com.hoang.moviedblearning.di.module

import com.hoang.moviedblearning.ui.screen.fragment.main.MainFragment
import com.hoang.moviedblearning.ui.screen.fragment.moviedetail.MovieDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailFragment(): MovieDetailFragment
}
