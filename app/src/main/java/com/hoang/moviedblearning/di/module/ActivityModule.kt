package com.hoang.moviedblearning.di.module

import com.hoang.moviedblearning.ui.screen.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMain(): MainActivity
}
