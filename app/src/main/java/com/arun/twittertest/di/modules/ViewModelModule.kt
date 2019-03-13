package com.arun.twittertest.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arun.twittertest.viewmodels.TwitterFeedViewModel
import com.arun.twittertest.viewmodels.TwitterTestViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TwitterFeedViewModel::class)
    abstract fun bindTwitterFeedViewModel(twitterFeedViewModel: TwitterFeedViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: TwitterTestViewModelFactory): ViewModelProvider.Factory
}
