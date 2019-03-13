package com.arun.twittertest.di

import android.app.Application
import com.arun.domain.di.DomainComponent
import com.arun.twittertest.di.modules.AppContextModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppContextModule::class], dependencies = [DomainComponent::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun domainComponent(domainComponent: DomainComponent): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: Application)
}