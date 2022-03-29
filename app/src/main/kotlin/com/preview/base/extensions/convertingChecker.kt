@file:Suppress("unused")

package com.preview.base.extensions

/**
 * Returns a value of type [T] or throws RuntimeException if [T] is null.
 * useful for network requests if the value should not be null.
 *
 * Example:
 * ```
 * getOrDie(name, "UserInfoEntity/userName")
 * ```
 * @param binding a message for the [RuntimeException]
 */
fun <T> getOrDie(item: T?, binding: String): T {
    return item ?: throw RuntimeException("'$binding' must not be null")
}

fun <T, R> getOrDie(items: List<T>?, converter: (T) -> R, binding: String): List<R> {
    val result = maybeConvert(items, converter)
    if (result == null) {
        throw RuntimeException("'$binding' must not be null")
    } else {
        return result
    }
}

fun <T> getNotEmptyOrDie(items: List<T>?, binding: String): List<T> {
    val notNullItems = getOrDie(items, binding)
    return notNullItems.ifEmpty { throw RuntimeException("'$binding' must not be empty") }
}

internal fun <T, R> maybeConvert(items: List<T>?, converter: (T) -> R): List<R>? {
    return items?.let { items.map(converter) }
}
