package com.hoang.moviedblearning.di.module

import com.hoang.moviedblearning.BuildConfig
import com.hoang.moviedblearning.data.api.AppApi
import com.hoang.moviedblearning.data.base.RxErrorHandlingCallAdapterFactory
import com.hoang.moviedblearning.utils.BASE_URL
import com.hoang.moviedblearning.utils.KEY
import com.hoang.moviedblearning.utils.PARAM_API_KEY
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        private const val CONNECT_TIMEOUT = 10L
        private const val READ_TIMEOUT = 10L
        private const val WRITE_TIMEOUT = 10L
    }

    @Provides
    @Singleton
    @Named("header")
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val newUrl = request.url.newBuilder().build()
            val newRequest =
                request.newBuilder()
                    .url(newUrl)
                    .method(request.method, request.body)
                    .build()
            chain.proceed(newRequest)
        }
    }

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            val url = request.url.newBuilder().addQueryParameter(PARAM_API_KEY, KEY)
                .build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        @Named("header") header: Interceptor,
        apiKeyInterceptor: Interceptor
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        clientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(logging)
        }
        clientBuilder.addInterceptor(header)
        clientBuilder.addInterceptor(apiKeyInterceptor)
        return clientBuilder.build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideAppApi(retrofit: Retrofit): AppApi {
        return retrofit.create(AppApi::class.java)
    }
}
