package com.preview.di

import com.preview.di.viewmodel.ViewModelModule
import dagger.Module

@Module(
    includes = [
        ApplicationModule::class,
        ViewModelModule::class,
        FeatureModules::class,
    ]
)
interface CoreModule