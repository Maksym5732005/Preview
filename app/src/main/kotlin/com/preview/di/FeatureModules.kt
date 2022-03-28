package com.preview.di

import com.preview.feature.welcome.di.WelcomeModule
import dagger.Module

@Module(
    includes = [
        WelcomeModule::class,
    ]
)
interface FeatureModules