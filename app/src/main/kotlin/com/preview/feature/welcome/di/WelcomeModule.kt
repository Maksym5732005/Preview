package com.preview.feature.welcome.di

import androidx.lifecycle.ViewModel
import com.preview.di.viewmodel.ViewModelKey
import com.preview.feature.welcome.data.WelcomeRepositoryImpl
import com.preview.feature.welcome.domain.WelcomeRepository
import com.preview.feature.welcome.presentation.WelcomeFragment
import com.preview.feature.welcome.presentation.WelcomeViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [WelcomeBindModule::class])
interface WelcomeModule {

    @ContributesAndroidInjector
    fun contributeWelcomeFragment(): WelcomeFragment

    @Binds
    @IntoMap
    @ViewModelKey(WelcomeViewModel::class)
    fun bindWelcomeViewModel(viewModel: WelcomeViewModel): ViewModel
}

@Module
interface WelcomeBindModule {
    @Binds
    fun bindWelcomeRepository(storage: WelcomeRepositoryImpl): WelcomeRepository
}