package com.preview

import com.preview.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.create()
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initRxJavaGlobalErrorHandler()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initRxJavaGlobalErrorHandler() {
        RxJavaPlugins.setErrorHandler {
            Timber.tag("RxJavaErrorHandler").e(it)
        }
    }
}