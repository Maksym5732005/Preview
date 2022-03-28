package com.preview.di

import com.preview.base.ThreadScheduler
import com.preview.base.ThreadSchedulerImpl
import dagger.Binds
import dagger.Module

@Module
interface ApplicationModule {

    @Binds
    fun provideThreadScheduler(scheduler: ThreadSchedulerImpl): ThreadScheduler
}