package com.arun.twittertest

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class TwitterApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}