package com.arun.twittertest.di

import android.app.Application
import com.arun.data.di.DataModule
import com.arun.data.di.NetworkModule
import com.arun.domain.di.DomainModule
import com.arun.twittertest.TwitterApplication
import com.arun.twittertest.di.modules.ActivityBuilderModule
import com.arun.twittertest.di.modules.AppContextModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, AppContextModule::class, ActivityBuilderModule::class,
        DomainModule::class, DataModule::class, NetworkModule::class]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: TwitterApplication)
}