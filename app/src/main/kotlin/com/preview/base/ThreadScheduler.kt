@file:Suppress("unused")

package com.preview.base

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single

interface ThreadScheduler {

    fun ui(): Scheduler
    fun io(): Scheduler
    fun computation(): Scheduler

    fun <T> toUiSingle() = { upstream: Single<T> -> upstream.observeOn(ui()) }

    fun <T> toIoSingle() = { upstream: Single<T> -> upstream.observeOn(io()) }

    fun <T> toComputationSingle() = { upstream: Single<T> -> upstream.observeOn(computation()) }

    fun <T> toUiObservable() = { upstream: Observable<T> -> upstream.observeOn(ui()) }

    fun <T> toIoObservable() = { upstream: Observable<T> -> upstream.observeOn(io()) }

    fun <T> ioToUiSingle() = { upstream: Single<T> -> upstream.subscribeOn(io()).observeOn(ui()) }

    fun <T> computationToUiSingle() = { upstream: Single<T> -> upstream.subscribeOn(computation()).observeOn(ui()) }

    fun <T> ioToUiObservable() = { upstream: Observable<T> -> upstream.subscribeOn(io()).observeOn(ui()) }

    fun <T> computationToUiObservable() = { upstream: Observable<T> ->
        upstream.subscribeOn(computation()).observeOn(ui())
    }

    fun <T> ioToUiFlowable() = { upstream: Flowable<T> -> upstream.subscribeOn(io()).observeOn(ui()) }

    fun <T> computationToUiFlowable() = { upstream: Flowable<T> ->
        upstream.subscribeOn(computation()).observeOn(ui())
    }

    fun computationToUiCompletable() = { upstream: Completable -> upstream.subscribeOn(computation()).observeOn(ui()) }

    fun ioCompletable() = { upstream: Completable -> upstream.subscribeOn(io()).observeOn(io()) }

    fun <T> computationObservable() = { upstream: Observable<T> ->
        upstream.subscribeOn(computation()).observeOn(computation())
    }

    fun ioToUiCompletable() = { upstream: Completable -> upstream.subscribeOn(io()).observeOn(ui()) }

    fun <T> ioToUiMaybe() = { upstream: Maybe<T> -> upstream.subscribeOn(io()).observeOn(ui()) }
}

fun <T> Single<T>.scheduleIoToUi(scheduler: ThreadScheduler): Single<T> {
    return compose(scheduler.ioToUiSingle())
}

fun <T> Maybe<T>.scheduleIoToUi(scheduler: ThreadScheduler): Maybe<T> {
    return compose(scheduler.ioToUiMaybe())
}

fun Completable.scheduleIoToUi(scheduler: ThreadScheduler): Completable {
    return compose(scheduler.ioToUiCompletable())
}

fun Completable.scheduleComputationToUi(scheduler: ThreadScheduler): Completable {
    return compose(scheduler.computationToUiCompletable())
}

fun Completable.scheduleIo(scheduler: ThreadScheduler): Completable {
    return compose(scheduler.ioCompletable())
}

fun <T> Single<T>.scheduleComputationToUi(scheduler: ThreadScheduler): Single<T> {
    return compose(scheduler.computationToUiSingle())
}

fun <T> Single<T>.scheduleToUi(scheduler: ThreadScheduler): Single<T> {
    return compose(scheduler.toUiSingle())
}

fun <T> Single<T>.scheduleToIo(scheduler: ThreadScheduler): Single<T> {
    return compose(scheduler.toIoSingle())
}

fun <T> Single<T>.scheduleToComputation(scheduler: ThreadScheduler): Single<T> {
    return compose(scheduler.toComputationSingle())
}

fun <T> Observable<T>.scheduleComputationToUi(scheduler: ThreadScheduler): Observable<T> {
    return compose(scheduler.computationToUiObservable())
}
fun <T> Observable<T>.scheduleComputation(scheduler: ThreadScheduler): Observable<T> {
    return compose(scheduler.computationObservable())
}

fun <T> Observable<T>.scheduleIoToUi(scheduler: ThreadScheduler): Observable<T> {
    return compose(scheduler.ioToUiObservable())
}

fun <T> Observable<T>.scheduleToUi(scheduler: ThreadScheduler): Observable<T> {
    return compose(scheduler.toUiObservable())
}

fun <T> Observable<T>.scheduleToIo(scheduler: ThreadScheduler): Observable<T> {
    return compose(scheduler.toIoObservable())
}

fun <T> Flowable<T>.scheduleComputationToUi(scheduler: ThreadScheduler): Flowable<T> {
    return compose(scheduler.computationToUiFlowable())
}

fun <T> Flowable<T>.scheduleIoToUi(scheduler: ThreadScheduler): Flowable<T> {
    return compose(scheduler.ioToUiFlowable())
}
