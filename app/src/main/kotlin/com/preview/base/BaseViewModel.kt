package com.preview.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel: ViewModel() {

    val event = SingleLiveEvent<Event>()

    private val compositeDisposable by lazy(LazyThreadSafetyMode.NONE) { CompositeDisposable() }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    protected fun <T : Disposable> T.autoDispose(): T {
        compositeDisposable.add(this)
        return this
    }
}