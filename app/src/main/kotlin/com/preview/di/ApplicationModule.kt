package com.preview.di

import android.content.Context
import com.preview.App
import com.preview.PreviewApi
import com.preview.base.AndroidResourceReader
import com.preview.base.ResourceReader
import com.preview.base.ThreadScheduler
import com.preview.base.ThreadSchedulerImpl
import com.preview.data.MockPreviewApi
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(app: App): Context = app

    @Provides
    @PerApplication
    fun provideThreadScheduler(): ThreadScheduler = ThreadSchedulerImpl()

    @Provides
    @PerApplication
    fun providePreviewApi(): PreviewApi = MockPreviewApi()

    @Provides
    @PerApplication
    fun provideResourceReader(context: Context): ResourceReader = AndroidResourceReader(context)
}