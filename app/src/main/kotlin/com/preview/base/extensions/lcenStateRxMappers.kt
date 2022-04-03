@file:Suppress("unused")

package com.preview.base.extensions

import com.preview.base.domain.LcenState
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

/**
 * Convert the result of [Completable] to an [Observable] of the type resulting from the LcenState conversion ([stateCreator]).
 */
fun <T> Completable.toLcenEventObservable(stateCreator: (LcenState<Unit>) -> T): Observable<T> {
    return andThen(Observable.fromCallable { stateCreator(LcenState.Content(Unit)) })
        .onErrorReturn { stateCreator(LcenState.Error(it)) }
        .startWith(stateCreator(LcenState.Loading))
}

/**
 * Convert the result of [Completable] to an Observable<LcenState<Unit>>
 */
fun Completable.toLcenEventObservable(): Observable<LcenState<Unit>> {
    return andThen(Observable.just<LcenState<Unit>>(LcenState.Content(Unit)))
        .onErrorReturn { LcenState.Error(it) }
        .startWith(LcenState.Loading)
}

fun <T> Completable.toLcenEventObservable(
    onSuccess: () -> T,
    onError: (Throwable) -> T,
    onLoading: T,
): Observable<T> {
    return andThen(Observable.fromCallable(onSuccess))
        .onErrorReturn(onError)
        .startWith(onLoading)
}

/**
 * Convert the result of [Observable] to an [Observable] of the type resulting from the LcenState conversion ([stateCreator]).
 */
fun <C : Any, T> Observable<C>.toLcenEventObservable(stateCreator: (LcenState<C>) -> T): Observable<T> {
    return map { stateCreator(LcenState.Content(it)) }
        .onErrorReturn { stateCreator(LcenState.Error(it)) }
        .startWith(stateCreator(LcenState.Loading))
}

/**
 * Convert the result of [Observable] to an Observable<LcenState<*>>
 */
fun <C : Any> Observable<C>.toLcenEventObservable(): Observable<LcenState<C>> {
    return map<LcenState<C>> { LcenState.Content(it) }
        .onErrorReturn { LcenState.Error(it) }
        .startWith(LcenState.Loading)
}

/**
 * Convert the result of [Observable] to an Observable<LcenState> of the same type with retry on error.
 */
fun <T : Any> Observable<T>.toRetryOnErrorLcenObservable(): Observable<LcenState<T>> {
    val errorRelay = PublishSubject.create<LcenState<T>>()
    return Observable.merge(
        doOnError { e -> errorRelay.onNext(LcenState.Error(e)) }
            .retry()
            .toLcenEventObservable(),
        errorRelay,
    )
}

fun <C, T> Observable<C>.toLcenEventObservable(
    onSuccess: (C) -> T,
    onError: (Throwable) -> T,
    onLoading: T,
): Observable<T> {
    return map(onSuccess)
        .onErrorReturn(onError)
        .startWith(onLoading)
}

/**
 * Convert the result of [Single] to an [Observable] of the type resulting from the LcenState conversion ([stateCreator]).
 */
fun <C : Any, T> Single<C>.toLcenEventObservable(stateCreator: (LcenState<C>) -> T): Observable<T> {
    return map { stateCreator(LcenState.Content(it)) }
        .onErrorReturn { stateCreator(LcenState.Error(it)) }
        .toObservable()
        .startWith(stateCreator(LcenState.Loading))
}

/**
 * Convert the result of [Single] to an Observable<LcenState<*>>
 */
fun <C : Any> Single<C>.toLcenEventObservable(): Observable<LcenState<C>> {
    return map<LcenState<C>> { LcenState.Content(it) }
        .onErrorReturn { LcenState.Error(it) }
        .toObservable()
        .startWith(LcenState.Loading)
}

fun <C, T> Single<C>.toLcenEventObservable(
    onSuccess: (C) -> T,
    onError: (Throwable) -> T,
    onLoading: T,
): Observable<T> {
    return map(onSuccess)
        .onErrorReturn(onError)
        .toObservable()
        .startWith(onLoading)
}

/**
 * Convert the result of [Maybe] to an [Observable] of the type resulting from the LcenState conversion ([stateCreator]).
 */
fun <C : Any, T> Maybe<C>.toLcenEventObservable(stateCreator: (LcenState<C>) -> T): Observable<T> {
    return map { stateCreator(LcenState.Content(it)) }
        .switchIfEmpty(Maybe.empty())
        .onErrorReturn { stateCreator(LcenState.Error(it)) }
        .toObservable()
        .startWith(stateCreator(LcenState.Loading))
}

/**
 * Convert the result of [Maybe] to an [Observable] of the same type.
 */
fun <C : Any> Maybe<C>.toLcenEventObservable(): Observable<LcenState<C>> {
    return map<LcenState<C>> { LcenState.Content(it) }
        .switchIfEmpty(Maybe.empty())
        .onErrorReturn { LcenState.Error(it) }
        .toObservable()
        .startWith(LcenState.Loading)
}
