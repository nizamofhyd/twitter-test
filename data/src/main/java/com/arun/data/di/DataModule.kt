package com.arun.data.di

import com.arun.data.repository.TwitterFeedRepository
import com.arun.data.service.TwitterService
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class DataModule {


    @Provides
    @Inject
    fun provideTwitterRepository(twitterService: TwitterService): TwitterFeedRepository {
        return TwitterFeedRepository(twitterService)
    }
}