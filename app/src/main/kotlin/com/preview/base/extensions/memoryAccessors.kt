@file:Suppress("unused")

package com.preview.base.extensions

import com.preview.base.HashMapMemory
import java.util.EnumMap
import java.util.concurrent.ConcurrentHashMap
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <M : HashMapMemory> M.fillFrom(memory: HashMapMemory): M = apply { putAll(memory) }

/**
 * Delegate for getting nullable values from [HashMapMemory].
 * If there is no value in memory by key, it will return null.
 */
fun <T : Any> HashMapMemory.nullable(propertyName: String? = null): ReadWriteProperty<Any?, T?> {
    return object : ReadWriteProperty<Any?, T?> {
        @Suppress("UNCHECKED_CAST")
        override fun getValue(thisRef: Any?, property: KProperty<*>): T? = get(propertyName ?: property.name) as T?

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
            if (value == null) {
                remove(propertyName ?: property.name)
            } else {
                put(propertyName ?: property.name, value)
            }
        }
    }
}

/**
 * Delegate for getting not null values from [HashMapMemory].
 * If there is no value in memory for the key, it will return the default value and put it in memory.
 */
fun <T : Any> HashMapMemory.notNull(propertyName: String? = null, default: () -> T): ReadWriteProperty<Any?, T> {
    return getOrPutProperty(propertyName, default)
}

/**
 * Delegate to get [HashMap] from [HashMapMemory].
 * If there is no value in memory for the key, it will return an empty [HashMap] (and put it in memory).
 */
fun <K, V> HashMapMemory.map(propertyName: String? = null): ReadWriteProperty<Any?, MutableMap<K, V>> {
    return getOrPutProperty(propertyName) { ConcurrentHashMap<K, V>() }
}

/**
 * Delegate to get [EnumMap] from [HashMapMemory].
 * If there is no value in memory for the key, it will return an empty [EnumMap] (and put it in memory).
 */
inline fun <reified K : Enum<K>, V> HashMapMemory.enumMap(
    propertyName: String? = null,
): ReadWriteProperty<Any?, EnumMap<K, V>> {
    return getOrPutProperty(propertyName) { EnumMap(K::class.java) }
}

/**
 * Delegate to get [MutableList] from [HashMapMemory].
 * If there is no value in memory for the key, it will return an empty list (and put it in memory).
 */
fun <T> HashMapMemory.list(propertyName: String? = null): ReadWriteProperty<Any?, MutableList<T>> {
    return getOrPutProperty(propertyName) { mutableListOf() }
}

/**
 * Delegate to get [MutableSet] from [HashMapMemory].
 * If there is no value in memory for the key, it will return an empty set (and put it in memory).
 */
fun <T> HashMapMemory.set(propertyName: String? = null): ReadWriteProperty<Any?, MutableSet<T>> {
    return getOrPutProperty(propertyName) { mutableSetOf() }
}

inline fun <T : Any> HashMapMemory.getOrPutProperty(
    propertyName: String? = null,
    crossinline default: () -> T,
): ReadWriteProperty<Any?, T> {
    return object : ReadWriteProperty<Any?, T> {
        @Suppress("UNCHECKED_CAST")
        override fun getValue(thisRef: Any?, property: KProperty<*>): T {
            return getOrPut(propertyName ?: property.name, default) as T
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            put(propertyName ?: property.name, value)
        }
    }
}
