package com.preview.feature.market.di

import androidx.lifecycle.ViewModel
import com.preview.di.viewmodel.ViewModelKey
import com.preview.feature.market.presentation.MarketFragment
import com.preview.feature.market.presentation.MarketViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [MarketsBindModule::class])
interface MarketsModule {

    @ContributesAndroidInjector
    fun contributeMarketFragment(): MarketFragment

    @Binds
    @IntoMap
    @ViewModelKey(MarketViewModel::class)
    fun bindMarketViewModel(viewModel: MarketViewModel): ViewModel
}

@Module
interface MarketsBindModule