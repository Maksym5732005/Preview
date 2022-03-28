@file:Suppress("EXPERIMENTAL_IS_NOT_ENABLED", "unused")

package com.preview.base

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * Преобразует изначальный тип данных A состояния Content в тип данных B
 */
inline fun <A : Any, B : Any> LcenState<A>.mapContent(
    mapper: (A) -> B,
): LcenState<B> {
    return when (this) {
        is LcenState.Content -> LcenState.Content(mapper(this.value))
        is LcenState.Loading -> this
        is LcenState.Error -> this
    }
}

/**
 * Преобразует изначальный тип данных A состояния Content в тип данных B
 */
inline fun <A : Any> LcenState<A>.mapError(
    mapper: (Throwable) -> Throwable,
): LcenState<A> {
    return when (this) {
        is LcenState.Content -> this
        is LcenState.Loading -> this
        is LcenState.Error -> LcenState.Error(mapper(this.value))
    }
}

fun <A : Any> LcenState<A>.mapContentUnit(): LcenState<Unit> = mapContent { }

@OptIn(ExperimentalContracts::class)
fun <T : Any> LcenState<T>.isContent(): Boolean {
    contract {
        returns(true) implies (this@isContent is LcenState.Content<T>)
    }
    return this is LcenState.Content
}

@OptIn(ExperimentalContracts::class)
fun <T : Any> LcenState<T>.isLoading(): Boolean {
    contract {
        returns(true) implies (this@isLoading is LcenState.Loading)
    }
    return this == LcenState.Loading
}

@OptIn(ExperimentalContracts::class)
fun <T : Any> LcenState<T>.isError(): Boolean {
    contract {
        returns(true) implies (this@isError is LcenState.Error)
    }
    return this is LcenState.Error
}

@OptIn(ExperimentalContracts::class)
fun LcenState<*>.isContentOrError(): Boolean {
    contract {
        returns(true) implies
            (this@isContentOrError is LcenState.Error || this@isContentOrError is LcenState.Content)
    }
    return isContent() || isError()
}

fun LcenState<Unit>.mergeWith(other: LcenState<Unit>): LcenState<Unit> {
    return when {
        this is LcenState.Loading || other is LcenState.Loading -> LcenState.Loading
        this is LcenState.Content && other is LcenState.Content -> LcenState.Content(Unit)
        this is LcenState.Error -> this
        other is LcenState.Error -> other
        else -> error("don't know how to merge $this and $other")
    }
}

fun <T1 : Any, T2 : Any, R : Any> LcenState<T1>.mergeWith(
    other: LcenState<T2>,
    contentZipper: (T1, T2) -> R,
): LcenState<R> {
    return when {
        this is LcenState.Loading || other is LcenState.Loading -> LcenState.Loading
        this is LcenState.Content && other is LcenState.Content ->
            LcenState.Content(contentZipper.invoke(this.asContent(), other.asContent()))
        this is LcenState.Error -> this
        other is LcenState.Error -> other
        else -> error("don't know how to merge $this and $other")
    }
}

fun LcenState<Unit>.onErrorMapToContent(): LcenState<Unit> {
    return if (this is LcenState.Error) LcenState.Content(Unit) else this
}
