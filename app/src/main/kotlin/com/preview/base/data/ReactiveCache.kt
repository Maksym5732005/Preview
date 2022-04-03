@file:Suppress("unused")

package com.preview.base.data

import com.preview.base.extensions.getOrPutProperty
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject
import io.reactivex.subjects.Subject
import kotlin.properties.ReadWriteProperty

/**
 * Key-value cache of type [T], to work with in reactive way.
 * If reactive access is not needed, just use [Map].
 *
 * For synchronous access, you need to use [set], [get] and [getAll], and for reactive access - their versions
 * with the `Live` suffix, i.e. [getLive] and [getAllLive] (there is no reactive version for [set]).
 *
 * When getting all entities from the cache (the [getAll] and [getAllLive] methods)
 * the order of the elements corresponds to the order in which they were added to the cache.
 */
class ReactiveCache<T : Any> constructor(strategy: SubscribeStrategy) {

    private val cache = mutableMapOf<String, T>()
    private val subject: Subject<Map<String, T>> = when (strategy) {
        SubscribeStrategy.NO_REPLAY -> PublishSubject.create()
        SubscribeStrategy.REPLAY_LAST -> BehaviorSubject.createDefault(cache)
        SubscribeStrategy.REPLAY -> ReplaySubject.create()
    }

    /** Synchronous retrieval of an entity by key from the cache. */
    @Synchronized
    operator fun get(key: String): T? = cache[key]

    /**
     * Getting an entity from the cache in reactive style.
     * Returns an [Observable] that keeps track of all changes to the entity in the cache.
     */
    fun getLive(key: String): Observable<T> {
        return subject.flatMap { map ->
            val entity = map[key]
            if (entity != null) Observable.just(entity) else Observable.empty()
        }
    }

    /** Synchronous retrieval of all entities from the cache. */
    @Synchronized
    fun getAll(): List<T> = cache.values.toList()

    /**
     * Retrieve all entities from the cache in a reactive style.
     * Returns an [Observable] that keeps track of all cache changes.
     */
    fun getAllLive(): Observable<List<T>> = subject.map { it.values.toList() }

    /** Entity changes in the cache by key. */
    operator fun set(key: String, details: T) {
        change { this[key] = details }
    }

    /** Update the cache to a new one. */
    fun replaceAll(newCache: Map<String, T>) {
        change {
            clear()
            putAll(newCache)
        }
    }

    /** Adding data to the cache. */
    fun putAll(additionalCache: Map<String, T>) {
        change {
            putAll(additionalCache)
        }
    }

    /** Clearing the cache. */
    fun clear() {
        change { clear() }
    }

    /** Clear cache by key. */
    fun remove(key: String) {
        change { remove(key) }
    }

    /**
     * Changing the cache. Should be used if more than one value needs to be changed.
     * The changes will be applied at the same time.
     *
     * Inside the [change] lambda, you should not perform long operations,
     * because while change is in progress, the cache cannot be accessed from elsewhere.
     */
    @Synchronized
    fun change(change: MutableMap<String, T>.() -> Unit) {
        cache.change()
        subject.onNext(cache.toMap())
    }

    /** Returns `true` if the key is found in the cache. */
    @Synchronized
    operator fun contains(key: String): Boolean = key in cache

    /** Returns `true` if there are no items in the cache. */
    @Synchronized
    fun isEmpty(): Boolean = cache.isEmpty()

    /** Cache change subscription strategy. */
    enum class SubscribeStrategy {
        /**
         * When subscribing, past values will not be passed.
         * The subscriber will only receive changes since the subscription.
         */
        NO_REPLAY,

        /** The subscriber will receive the latest cache value at the time of the subscription. */
        REPLAY_LAST,

        /** The subscriber will receive all cache values up to the moment of subscription, as well as all subsequent ones. */
        REPLAY
    }
}

/**
 * Delegate to get [ReactiveCache] from [HashMapMemory].
 * If there is no value in memory for the key, it will return a new cache (and put it in memory).
 */
fun <T : Any> HashMapMemory.reactiveCache(
    strategy: ReactiveCache.SubscribeStrategy = ReactiveCache.SubscribeStrategy.REPLAY_LAST,
    propertyName: String? = null,
): ReadWriteProperty<Any?, ReactiveCache<T>> {
    return getOrPutProperty(propertyName) { ReactiveCache(strategy) }
}
