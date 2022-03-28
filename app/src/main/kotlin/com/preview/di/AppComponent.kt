package com.preview.di

import com.preview.App
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(modules = [CoreModule::class, AndroidSupportInjectionModule::class])
interface AppComponent: AndroidInjector<App>