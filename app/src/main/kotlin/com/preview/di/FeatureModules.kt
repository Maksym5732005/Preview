package com.preview.di

import com.preview.feature.market.di.MarketsModule
import com.preview.feature.permission.di.PermissionModule
import com.preview.feature.welcome.di.WelcomeModule
import dagger.Module

@Module(
    includes = [
        WelcomeModule::class,
        MarketsModule::class,
        PermissionModule::class,
    ]
)
interface FeatureModules