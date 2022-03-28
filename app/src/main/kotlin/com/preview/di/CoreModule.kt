package com.preview.di

import com.preview.di.viewmodel.ViewModelModule
import com.preview.feature.welcome.di.WelcomeModule
import dagger.Module

@Module(
    includes = [
        ViewModelModule::class,
        WelcomeModule::class,
    ]
)
interface CoreModule