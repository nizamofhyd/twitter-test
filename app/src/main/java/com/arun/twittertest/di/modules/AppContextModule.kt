package com.arun.twittertest.di.modules

import android.app.Application
import android.content.Context
import com.arun.twittertest.di.qualifiers.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class AppContextModule {

    @Provides
    @ApplicationScope
    fun provideApplicationContext(application: Application): Context {
        return application
    }
}