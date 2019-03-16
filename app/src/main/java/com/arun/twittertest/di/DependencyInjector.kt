package com.arun.twittertest.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.arun.twittertest.TwitterApplication
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

class DependencyInjector {

    companion object {

        fun init(application: TwitterApplication) {

            DaggerApplicationComponent
                .builder()
                .application(application)
                .build()
                .inject(application)

            application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {

                override fun onActivityPaused(activity: Activity?) {

                }

                override fun onActivityResumed(activity: Activity?) {

                }

                override fun onActivityStarted(activity: Activity?) {

                }

                override fun onActivityDestroyed(activity: Activity?) {

                }

                override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

                }

                override fun onActivityStopped(activity: Activity?) {

                }

                override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                    injectActivityIfRequired(activity)
                }
            })

        }

        private fun injectActivityIfRequired(activity: Activity?) {
            if (activity is HasSupportFragmentInjector || activity is Injectable) {
                AndroidInjection.inject(activity)
            }
            if (activity is FragmentActivity) {
                activity.supportFragmentManager
                    .registerFragmentLifecycleCallbacks(
                        object : FragmentManager.FragmentLifecycleCallbacks() {
                            override fun onFragmentCreated(
                                fm: FragmentManager,
                                f: Fragment,
                                savedInstanceState: Bundle?
                            ) {
                                if (f is Injectable) {
                                    AndroidSupportInjection.inject(f)
                                }
                            }
                        }, true
                    )
            }
        }

    }
}