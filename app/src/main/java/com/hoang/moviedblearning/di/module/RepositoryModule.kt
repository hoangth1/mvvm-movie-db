package com.hoang.moviedblearning.di.module

import com.google.gson.Gson
import com.hoang.moviedblearning.data.repository.Repository
import com.hoang.moviedblearning.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    fun proveAppRepository(repositoryImpl: RepositoryImpl):Repository= repositoryImpl
}
