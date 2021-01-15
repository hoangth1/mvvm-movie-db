package com.hoang.moviedblearning.di.module

import com.hoang.moviedblearning.ui.screen.fragment.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

}
