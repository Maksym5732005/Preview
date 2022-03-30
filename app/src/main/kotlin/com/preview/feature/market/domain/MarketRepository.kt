package com.preview.feature.market.domain

import com.preview.base.LcenState
import com.preview.feature.market.domain.model.MarketInfo
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface MarketRepository {
    fun fetchMarketState(skipCache: Boolean): Completable
    fun getMarketStateLive(): Observable<MarketInfo>
}