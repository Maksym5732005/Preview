@file:Suppress("unused")

package com.preview.base.extensions

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.disposables.Disposable
import timber.log.Timber

@Suppress("nothing_to_inline") // инлайн необходим для того чтобы тимбер правильно формировал тэг
@CheckReturnValue
inline fun <T> Observable<T>.subscribeWithErrorLog(noinline onNext: (T) -> Unit = {}): Disposable {
    return subscribe(onNext, Timber::e)
}

@Suppress("nothing_to_inline") // инлайн необходим для того чтобы тимбер правильно формировал тэг
@CheckReturnValue
inline fun <T> Observable<T>.subscribeWithErrorLog(
    noinline onNext: (T) -> Unit,
    noinline onComplete: () -> Unit = {},
): Disposable {
    return subscribe(onNext, Timber::e, onComplete)
}

@Suppress("nothing_to_inline") // инлайн необходим для того чтобы тимбер правильно формировал тэг
@CheckReturnValue
inline fun <T> Single<T>.subscribeWithErrorLog(noinline onSuccess: (T) -> Unit = {}): Disposable {
    return subscribe(onSuccess, Timber::e)
}

@Suppress("nothing_to_inline") // инлайн необходим для того чтобы тимбер правильно формировал тэг
@CheckReturnValue
inline fun <T> Flowable<T>.subscribeWithErrorLog(noinline onNext: (T) -> Unit = {}): Disposable {
    return subscribe(onNext, Timber::e)
}

@Suppress("nothing_to_inline") // инлайн необходим для того чтобы тимбер правильно формировал тэг
@CheckReturnValue
inline fun <T> Flowable<T>.subscribeWithErrorLog(
    noinline onNext: (T) -> Unit,
    noinline onComplete: () -> Unit = {},
): Disposable {
    return subscribe(onNext, Timber::e, onComplete)
}

@Suppress("nothing_to_inline") // инлайн необходим для того чтобы тимбер правильно формировал тэг
@CheckReturnValue
inline fun <T> Maybe<T>.subscribeWithErrorLog(noinline onSuccess: (T) -> Unit = {}): Disposable {
    return subscribe(onSuccess, Timber::e)
}

@Suppress("nothing_to_inline") // инлайн необходим для того чтобы тимбер правильно формировал тэг
@CheckReturnValue
inline fun <T> Maybe<T>.subscribeWithErrorLog(
    noinline onSuccess: (T) -> Unit,
    noinline onComplete: () -> Unit = {},
): Disposable {
    return subscribe(onSuccess, Timber::e, onComplete)
}

@Suppress("nothing_to_inline") // инлайн необходим для того чтобы тимбер правильно формировал тэг
@CheckReturnValue
inline fun Completable.subscribeWithErrorLog(noinline onComplete: () -> Unit = {}): Disposable {
    return subscribe(onComplete, Timber::e)
}
