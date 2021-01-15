package com.hoang.moviedblearning.di.module

import android.app.Application
import android.content.Context
import com.hoang.moviedblearning.rx.AppSchedulerProvider
import com.matech.yogahealthfitness.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, NetworkModule::class, RepositoryModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun providerSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }
}
