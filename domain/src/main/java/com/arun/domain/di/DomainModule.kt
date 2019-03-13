package com.arun.domain.di

import com.arun.data.repository.ITwitterFeedRepository
import com.arun.domain.usecases.TwitterUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class DomainModule {

    @Provides
    @Inject
    fun provideTwitterUseCase(twitterFeedRepository: ITwitterFeedRepository): TwitterUseCase {
        return TwitterUseCase(twitterFeedRepository)
    }
}