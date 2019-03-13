package com.arun.domain.di

import com.arun.data.di.DataComponent
import dagger.Component

@Component(modules = [DomainModule::class], dependencies = [DataComponent::class])
interface DomainComponent {

    @Component.Builder
    interface Builder {

        fun dataComponent(dataComponent: DataComponent): Builder

        fun build(): DomainComponent
    }
}