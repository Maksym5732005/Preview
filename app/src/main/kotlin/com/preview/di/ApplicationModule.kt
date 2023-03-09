package com.preview.di

import android.content.Context
import com.preview.App
import com.preview.base.data.MockPreviewApi
import com.preview.base.domain.PreviewApi
import com.preview.base.domain.ThreadScheduler
import com.preview.base.domain.ThreadSchedulerImpl
import com.preview.base.presentation.AndroidResourceReader
import com.preview.base.presentation.ResourceReader
import com.preview.feature.market.data.MarketApi
import com.preview.feature.welcome.data.WelcomeApi
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @[Provides PerApplication]
    fun provideContext(app: App): Context = app

    @[Provides PerApplication]
    fun provideThreadScheduler(): ThreadScheduler = ThreadSchedulerImpl()

    @[Provides PerApplication]
    fun providePreviewApi(): PreviewApi = MockPreviewApi()

    @[Provides PerApplication]
    fun provideWelcomeApi(): WelcomeApi = providePreviewApi()

    @[Provides PerApplication]
    fun provideMarketApi(): MarketApi = providePreviewApi()

    @[Provides PerApplication]
    fun provideResourceReader(context: Context): ResourceReader = AndroidResourceReader(context)
}