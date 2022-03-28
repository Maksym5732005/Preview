package com.preview.di

import com.preview.feature.market.di.MarketsModule
import com.preview.feature.welcome.di.WelcomeModule
import dagger.Module

@Module(
    includes = [
        WelcomeModule::class,
        MarketsModule::class,
    ]
)
interface FeatureModules