package com.preview.di

import com.preview.di.viewmodel.ViewModelModule
import com.preview.feature.welcome.di.WelcomeModule
import dagger.Module

@Module(
    includes = [
        ApplicationModule::class,
        ViewModelModule::class,
        WelcomeModule::class,
    ]
)
interface CoreModule