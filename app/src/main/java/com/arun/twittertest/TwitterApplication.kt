package com.arun.twittertest

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.arun.twittertest.di.DependencyInjector

class TwitterApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        DependencyInjector.init(this)
    }
}