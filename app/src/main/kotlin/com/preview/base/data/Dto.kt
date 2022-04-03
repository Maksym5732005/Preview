@file:Suppress("unused")

package com.preview.base.data

interface Dto<out T> {
    fun convert(): T
}

fun <T : Any> List<Dto<T?>>?.convert(): List<T> {
    return this?.mapNotNull { it.convert() } ?: emptyList()
}

fun <T : Any, R : Any> List<Dto<T?>>?.convert(converter: (T) -> R): List<R> {
    return this?.mapNotNull { it.convert()?.let(converter) } ?: emptyList()
}

fun <T, D : Dto<T>, R> D.convert(converter: (T) -> R): R = convert().let(converter)
