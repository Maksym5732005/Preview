package com.preview.feature.market.domain

import com.preview.feature.market.domain.model.MarketInfo
import com.preview.feature.market.domain.model.MarketItemMetal
import io.reactivex.Completable
import io.reactivex.Observable

interface MarketRepository {
    fun fetchMarketState(skipCache: Boolean): Completable
    fun getMarketStateLive(): Observable<MarketInfo>
    fun fetchPreciousMetals(skipCache: Boolean): Completable
    fun getPreciousMetalsLive(): Observable<List<MarketItemMetal>>
}