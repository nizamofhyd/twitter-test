package com.arun.domain.di

import com.arun.data.di.DataModule
import com.arun.data.di.NetworkModule
import dagger.Component


@Component(modules = [DomainModule::class])

interface DomainComponent {

    @Component.Builder
    interface Builder {

        fun build(): DomainComponent
    }
}