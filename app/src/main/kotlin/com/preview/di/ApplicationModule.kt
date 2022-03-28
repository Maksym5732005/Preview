package com.preview.di

import com.preview.PreviewApi
import com.preview.base.ThreadScheduler
import com.preview.base.ThreadSchedulerImpl
import com.preview.data.MockPreviewApi
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    @PerApplication
    fun provideThreadScheduler(): ThreadScheduler = ThreadSchedulerImpl()

    @Provides
    @PerApplication
    fun providePreviewApi(): PreviewApi = MockPreviewApi()
}