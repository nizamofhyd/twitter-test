package com.arun.data.di

import dagger.Component

@Component(modules = [DataModule::class, NetworkModule::class])
interface DataComponent {

    @Component.Builder
    interface Builder {

        fun build(): DataComponent
    }
}