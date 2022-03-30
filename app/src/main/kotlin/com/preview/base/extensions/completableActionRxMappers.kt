@file:Suppress("unused")

package com.preview.base.extensions

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

fun <T : Any> Observable<T>.flatMapCompletableAction(action: (T) -> Unit): Completable {
    return flatMapCompletable { param -> Completable.fromAction { action.invoke(param) } }
}

fun <T : Any> Single<T>.flatMapCompletableAction(action: (T) -> Unit): Completable {
    return flatMapCompletable { param -> Completable.fromAction { action.invoke(param) } }
}

fun <T : Any> Maybe<T>.flatMapCompletableAction(action: (T) -> Unit): Completable {
    return flatMapCompletable { param -> Completable.fromAction { action.invoke(param) } }
}

fun Completable.andThenAction(action: () -> Unit): Completable {
    return andThen(Completable.fromAction(action))
}
