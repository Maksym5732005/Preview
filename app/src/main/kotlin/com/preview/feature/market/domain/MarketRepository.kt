package com.preview.feature.market.domain

import com.preview.feature.market.domain.model.MarketInfo
import com.preview.feature.market.domain.model.MarketItemIndex
import com.preview.feature.market.domain.model.MarketItemMetal
import io.reactivex.Completable
import io.reactivex.Observable

interface MarketRepository {
    /**
     * Request update for Market state.
     * @param skipCache use `False` to get data from a local cache if available. `True` to request fresh data from remote.
     */
    fun fetchMarketState(skipCache: Boolean): Completable
    /**
     * Subscribe for Market state changes.
     */
    fun getMarketStateLive(): Observable<MarketInfo>
    /**
     * Request update for Precious metals.
     * @param skipCache use `False` to get data from a local cache if available. `True` to request fresh data from remote.
     */
    fun fetchPreciousMetals(skipCache: Boolean): Completable
    /**
     * Subscribe for Precious metals changes.
     */
    fun getPreciousMetalsLive(): Observable<List<MarketItemMetal>>
    /**
     * Request update for Base metals.
     * @param skipCache use `False` to get data from a local cache if available. `True` to request fresh data from remote.
     */
    fun fetchBaseMetals(skipCache: Boolean): Completable
    /**
     * Subscribe for Base metals changes.
     */
    fun getBaseMetalsLive(): Observable<List<MarketItemMetal>>
    /**
     * Request update for Indices.
     * @param skipCache use `False` to get data from a local cache if available. `True` to request fresh data from remote.
     */
    fun fetchIndices(skipCache: Boolean): Completable
    /**
     * Subscribe for Indices changes.
     */
    fun getIndicesLive(): Observable<List<MarketItemIndex>>
}