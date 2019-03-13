package com.arun.data.di

import com.arun.data.service.TwitterService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Inject

@Module
class NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        val baseUrl = "https://api.twitter.com"
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(OkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Inject
    fun provideTwitterService(retrofit: Retrofit): TwitterService {
        return retrofit.create(TwitterService::class.java)
    }

}