package com.arun.domain.di

import com.arun.data.repository.TwitterFeedRepository
import com.arun.domain.usecases.TwitterUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class DomainModule {

    @Provides
    @Inject
    fun provideTwitterUseCase(twitterFeedRepository: TwitterFeedRepository): TwitterUseCase {
        return TwitterUseCase(twitterFeedRepository)
    }
}