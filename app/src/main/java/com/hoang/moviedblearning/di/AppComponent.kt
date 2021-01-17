package com.hoang.moviedblearning.di

import android.app.Application
import com.hoang.moviedblearning.MainApplication
import com.hoang.moviedblearning.di.module.ActivityModule
import com.hoang.moviedblearning.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (ActivityModule::class)])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(mainApplication: MainApplication)
}
