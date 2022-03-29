@file:Suppress("unused")

package com.preview.di

import androidx.lifecycle.ViewModelProvider
import com.preview.di.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        ApplicationModule::class,
        FeatureModules::class,
    ]
)
interface CoreModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}