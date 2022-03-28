@file:Suppress("unused")

package com.preview.base.extensions

import com.preview.base.LcenState
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

/**
 * Преобразуют результат Completable в Observable типа, получаемого в результате
 * преобразования LcenState
 */
fun <T> Completable.toLcenEventObservable(stateCreator: (LcenState<Unit>) -> T): Observable<T> {
    return andThen(Observable.fromCallable { stateCreator(LcenState.Content(Unit)) })
        .onErrorReturn { stateCreator(LcenState.Error(it)) }
        .startWith(stateCreator(LcenState.Loading))
}

/**
 * Преобразуют результат Completable в Observable<LcenState<Unit>>
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
 * Преобразуют результат Observable в Observable типа, получаемого в результате
 * преобразования LcenState
 */
fun <C : Any, T> Observable<C>.toLcenEventObservable(stateCreator: (LcenState<C>) -> T): Observable<T> {
    return map { stateCreator(LcenState.Content(it)) }
        .onErrorReturn { stateCreator(LcenState.Error(it)) }
        .startWith(stateCreator(LcenState.Loading))
}

/**
 * Преобразуют результат Observable в Observable<LcenState<Unit>>
 */
fun <C : Any> Observable<C>.toLcenEventObservable(): Observable<LcenState<C>> {
    return map<LcenState<C>> { LcenState.Content(it) }
        .onErrorReturn { LcenState.Error(it) }
        .startWith(LcenState.Loading)
}

/**
 * Преобразуют результат Observable в Observable<LcenState> того же типа
 * Автоматически переподписывается на апстрим в случае ошибки
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
 * Преобразуют результат Single в Observable типа, получаемого в результате
 * преобразования LcenState
 */
fun <C : Any, T> Single<C>.toLcenEventObservable(stateCreator: (LcenState<C>) -> T): Observable<T> {
    return map { stateCreator(LcenState.Content(it)) }
        .onErrorReturn { stateCreator(LcenState.Error(it)) }
        .toObservable()
        .startWith(stateCreator(LcenState.Loading))
}

/**
 * Преобразуют результат Single в Observable<LcenState<Unit>>
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
 * Преобразуют результат Maybe в Observable типа, получаемого в результате
 * преобразования LcenState
 */
fun <C : Any, T> Maybe<C>.toLcenEventObservable(stateCreator: (LcenState<C>) -> T): Observable<T> {
    return map { stateCreator(LcenState.Content(it)) }
        .switchIfEmpty(Maybe.empty())
        .onErrorReturn { stateCreator(LcenState.Error(it)) }
        .toObservable()
        .startWith(stateCreator(LcenState.Loading))
}

/**
 * Преобразуют результат Maybe в Observable типа, получаемого в результате
 * преобразования LcenState
 */
fun <C : Any> Maybe<C>.toLcenEventObservable(): Observable<LcenState<C>> {
    return map<LcenState<C>> { LcenState.Content(it) }
        .switchIfEmpty(Maybe.empty())
        .onErrorReturn { LcenState.Error(it) }
        .toObservable()
        .startWith(LcenState.Loading)
}
