package com.preview.feature.permission.di

import androidx.lifecycle.ViewModel
import com.preview.di.viewmodel.ViewModelKey
import com.preview.feature.permission.presentation.PermissionFragment
import com.preview.feature.permission.presentation.PermissionViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [PermissionBindModule::class])
interface PermissionModule {

    @ContributesAndroidInjector
    fun contributePermissionFragment(): PermissionFragment

    @Binds
    @IntoMap
    @ViewModelKey(PermissionViewModel::class)
    fun bindPermissionViewModel(viewModel: PermissionViewModel): ViewModel
}

@Module
interface PermissionBindModule